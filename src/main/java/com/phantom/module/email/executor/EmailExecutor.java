package com.phantom.module.email.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016/9/24.
 */
@Component
public class EmailExecutor {
    private ExecutorService exec = Executors.newCachedThreadPool();
    @Autowired
    private EmailSendTask emailSendTask;

    public void exec(){
        Future future = exec.submit(emailSendTask);
        System.out.println(future.isDone());
        //exec.shutdown();
    }

}
