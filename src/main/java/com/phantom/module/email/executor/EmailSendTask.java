package com.phantom.module.email.executor;


import com.phantom.module.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2016/9/24.
 */
@Component
public class EmailSendTask implements Callable<String> {
    @Autowired
    private EmailService emailService;

    @Override
    public String call() throws Exception {
        emailService.sendEmail();
        return "result of TaskWithResult ";
    }
}
