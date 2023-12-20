package com.systex.dddlab.runner;

import com.systex.dddlab.command.PlaceOrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Component
@Slf4j
@Order(2)
public class Runner2 implements CommandLineRunner {
    @Autowired
    private CommandGateway commandGateway;

    @Override
    public void run(String... args) throws Exception {
        PlaceOrderCommand command1 = new PlaceOrderCommand(UUID.randomUUID().toString(),
                "Kevin", 30);
        PlaceOrderCommand command2 = new PlaceOrderCommand(UUID.randomUUID().toString(),
                "Kevin2", 300);
        commandGateway.send(command1, (rcmd1, result1) -> {
            if (!result1.isExceptional()) { // success
                log.info("command1執行正確,detail={}", rcmd1);
                commandGateway.send(command2, (rcmd2, result2) -> {
                    if (!result2.isExceptional()) {
                        log.info("command2執行正確,detail={}", rcmd2);
                    }
                });
            }
        });

    }
}
