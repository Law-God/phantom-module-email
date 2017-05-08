package com.phantom.module.email;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/9/25.
 */
public class CallableDemo {
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for(int i=0;i<10;i++){
            results.add(exec.submit(new TaskWithResult(i)));
        }
        try{
            for(Future<String> fs : results){
                System.out.println(fs.get());
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            exec.shutdown();
        }

    }
}


class TaskWithResult implements Callable<String> {
    private int id;
    public TaskWithResult(int id){
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }
}
