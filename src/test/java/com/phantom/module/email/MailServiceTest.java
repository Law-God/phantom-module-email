package com.phantom.module.email;

import com.phantom.module.email.dao.EmailDao;
import com.phantom.module.email.dao.EmailEnum;
import com.phantom.module.email.executor.EmailExecutor;
import com.phantom.module.email.service.EmailService;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 张志凯 https://github.com/Law-God/phantom-util
 * email
 * MailServiceTest
 * 2016-09-14 17:25
 */
@ContextConfiguration("classpath:applicationContext.xml")

public class MailServiceTest extends AbstractJUnit4SpringContextTests {
    @Resource
    private EmailClient emailClient;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailDao emailDao;

    @Test
    public void testSendMail() {

        //创建邮件
        MailBean mailBean = new MailBean();
        mailBean.setFrom("15959069401@163.com");
        mailBean.setSubject("hello world");
        mailBean.setToEmails(new String[]{"767030396@qq.com"});
        mailBean.setTemplate("hello ${userName} !<a href='www.baidu.com' >baidu</a>");
        Map map = new HashMap();
        map.put("userName", "15959069401@163.com");
        mailBean.setData(map);

        //发送邮件
        try {
            emailClient.send(mailBean);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDataBase(){
        String sql = "select * from t_email";
        List list = jdbcTemplate.queryForList(sql);
        System.out.println(list.size());
    }


    @Test
    public void testEmailUpadate(){
        Assert.assertEquals(1,emailDao.updateEmailStatus("1","准备发送邮件",EmailEnum.STATUS_1));
    }

    @Test
    public void testEmailService(){
        try {
            emailService.sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private EmailExecutor emailExecutor;
    @Test
    public void testEmailExecutor(){
        emailExecutor.exec();
        while (true){}
    }


}
