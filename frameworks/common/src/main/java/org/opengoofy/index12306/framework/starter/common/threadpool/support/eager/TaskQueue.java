package org.opengoofy.index12306.framework.starter.common.threadpool.support.eager;

import lombok.Setter;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class TaskQueue <R extends Runnable>extends LinkedBlockingQueue<Runnable> {
    @Setter
    private EagerThreadPoolExecutor executor;

    @Override
    public boolean offer(Runnable runnable) {
        int currentPoolThreadSize = executor.getPoolSize();
        //当前线程小于核心线程，加入阻塞队列，由核心线程处理
        if(currentPoolThreadSize<executor.getCorePoolSize()){
          return super.offer(runnable);
        }
        //当前线程小于最大线程数，返回false创建非核心线程
        if(currentPoolThreadSize<executor.getMaximumPoolSize()){
            return false;
        }
        //当前线程数大于最大线程数，加入阻塞队列
        return super.offer(runnable);
    }

    public boolean retryOffer(Runnable runnable, long timeOut, TimeUnit timeUnit) throws InterruptedException {
        if (executor.isShutdown()){
             throw new  RejectedExecutionException("Executor is Shutdown");
        }
        return  super.offer(runnable,timeOut,timeUnit);
    }

}
