package com.phantom.module.email.service;

import com.phantom.module.email.EmailClient;
import com.phantom.module.email.MailBean;
import com.phantom.module.email.dao.EmailDao;
import com.phantom.module.email.dao.EmailEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/25.
 */
@Component
public class EmailService {
    private Log log = LogFactory.getLog(EmailClient.class);
    @Autowired
    private EmailDao emailDao;
    @Resource
    private EmailClient emailClient;
    
    public void sendEmail() throws Exception {

       long startTime = System.currentTimeMillis();
       boolean flag = false;
        List<Map<String,Object>> emails = emailDao.queryEmails();
        for(int i=0,len=emails.size();i<len;i++) {
            Map<String, Object> email = emails.get(i);
            String emaiId = email.get("id")+"";
            int result = emailDao.updateEmailStatus(emaiId, "准备发送",EmailEnum.STATUS_1);
            if(result < 1){
                if(log.isErrorEnabled()){
                    log.error("邮件发送模块；邮件准备发送失败，邮件ID="+emaiId);
                }
                continue;
            }
            MailBean mailBean = new MailBean();
            mailBean.setFrom(email.get("sender") + "");
            mailBean.setSubject(email.get("title") + "");
            mailBean.setToEmails(new String[]{email.get("receiver") + ""});

            mailBean.setTemplate(email.get("content") + "");
            Map map = new HashMap();
            mailBean.setData(map);

            try {
                flag = emailClient.send(mailBean);
            } catch (MessagingException e) {
                e.printStackTrace();
            }finally {
                if(flag){
                    result = emailDao.updateEmailStatus(emaiId, "发送成功",EmailEnum.STATUS_2);
                    if(result < 1){
                        if(log.isErrorEnabled()){
                            log.error("邮件发送模块；邮件发送失败，邮件ID="+emaiId);
                        }
                        continue;
                    }
                }
            }

        }
        long endTime = System.currentTimeMillis();
        System.out.println("本次运行时间="+(endTime-startTime));
    }

}
