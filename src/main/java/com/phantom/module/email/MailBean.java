package com.phantom.module.email;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 张志凯 https://github.com/Law-God/phantom-util
 * email
 * com.phantom.module.email.MailBean
 * 2016-09-14 17:18
 */
public class MailBean {
    private String from;
    private String fromName;
    private String[] toEmails;

    private String subject;

    private Map data = new HashMap();          //邮件模版数据
    private String template;    //邮件模板

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getToEmails() {
        return toEmails;
    }

    public void setToEmails(String[] toEmails) {
        this.toEmails = toEmails;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
