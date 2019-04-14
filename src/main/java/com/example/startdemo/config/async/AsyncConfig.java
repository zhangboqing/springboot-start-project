package com.example.startdemo.config.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhangboqing
 * @date 2018/8/6
 * <p>
 * 开启多线程操作，注解方式
 * 在需要进行异步处理的方法上加上@Async注解
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    /**
     * 设置线程池基本大小值, 线程池维护线程的最少数量
     */
    private int corePoolSize = 10;
    /**
     * 设置线程池最大值
     */
    private int maxPoolSize = 200;
    /**
     * 线程池所使用的缓冲队列大小
     */
    private int queueCapacity = 1024;
    /**
     * 配置线程最大空闲时间
     */
    private int keepAliveSeconds = 50000;

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("AsyncExecutor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }
}

