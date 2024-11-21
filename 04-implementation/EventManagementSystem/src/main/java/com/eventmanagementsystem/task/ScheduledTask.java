package com.eventmanagementsystem.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    // 每10秒打印一次日志
    @Scheduled(fixedRate = 10000)
    public void runTask() {
        System.out.println("Scheduled task is running...");
    }
}
