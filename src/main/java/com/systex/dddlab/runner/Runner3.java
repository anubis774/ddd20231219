package com.systex.dddlab.runner;

import com.systex.dddlab.command.PlaceOrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Component
@Slf4j
@Order(3)
public class Runner3 implements CommandLineRunner {
    @Autowired
    private CommandBus commandBus;

    @Override
    public void run(String... args) throws Exception {
        PlaceOrderCommand command1 = new PlaceOrderCommand(UUID.randomUUID().toString(),
                "Kevin", 30);
        PlaceOrderCommand command2 = new PlaceOrderCommand(UUID.randomUUID().toString(),
                "Kevin2", 300);
        CommandMessage<PlaceOrderCommand> message1 =
                GenericCommandMessage.asCommandMessage(command1);
        CommandMessage<PlaceOrderCommand> message2 =
                GenericCommandMessage.asCommandMessage(command2);
        log.info("用bus送message1");
        commandBus.dispatch(message1,(msg1,result1)->{
            if (!result1.isExceptional()){
                log.info("message1 success");
                log.info("用bus送message2");
                commandBus.dispatch(message2, (msg2, result2) -> {
                    if (!result2.isExceptional()) {
                        log.info("message2 success");
                    }
                });
            }
        });

    }
}
