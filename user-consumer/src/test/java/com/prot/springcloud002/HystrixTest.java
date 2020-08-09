package com.prot.springcloud002;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HystrixTest extends HystrixCommand<String> {

    protected HystrixTest(HystrixCommandGroupKey group) {
        super(group);
    }

    public static void main(String[] args) throws Exception {
        Future<String> futureResult = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("test")).queue();
//        String result = "";
//        try {
//            result = futureResult.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        final String result = futureResult.get();
        System.out.println("result = " + result);
    }

    @Override
    protected String run() throws Exception {
        System.out.println("Executing ....");
        int i = 1/0;
        return "1/0 is OK";
    }

    @Override
    protected String getFallback() {
        return "GetFallback ...";
    }
}
