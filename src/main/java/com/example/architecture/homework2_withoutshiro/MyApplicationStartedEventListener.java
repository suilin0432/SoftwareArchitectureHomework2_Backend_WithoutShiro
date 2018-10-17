package com.example.architecture.homework2_withoutshiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;


public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    private Logger logger = LoggerFactory.getLogger(MyApplicationStartedEventListener.class);
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        logger.info("MyApplicationStartedEventListener 触发");
        System.out.println("MyApplicationStartedEventListener 触发");
    }
}
