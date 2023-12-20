package com.systex.dddlab.runner;

import com.systex.dddlab.command.CreateCourseCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@Order(5)
public class Runner5 implements CommandLineRunner {
    @Autowired
    private CommandGateway commandGateway;

    @Override
    public void run(String... args) throws Exception {
        CreateCourseCommand command1 = new CreateCourseCommand(UUID.randomUUID().toString(), "spring boot 3");
        commandGateway.send(command1, (m, r) -> {
            if (!r.isExceptional()) {
                log.info("課程創建成功, payload={},id={}", r.getPayload(), r.getIdentifier());
            } else {
                log.info("oops, reason={}", r.exceptionResult().toString());
            }
        });
    }
}
