package com.systex.dddlab.runner;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.Configurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(1)
public class Runner1 implements CommandLineRunner {
    @Autowired
    private Configurer configurer;

    @Override
    public void run(String... args) throws Exception {
        log.info("取得ddd axon的configurer={}", configurer);
    }
}
