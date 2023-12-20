package com.systex.dddlab.runner;

import com.systex.dddlab.command.CreateUserAccountCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@Order(4)
public class Runner4 implements CommandLineRunner {
    @Autowired
    private CommandGateway commandGateway;

    @Override
    public void run(String... args) throws Exception {
        String s1 = UUID.randomUUID().toString();
        log.info("uuid={}",s1);
        CreateUserAccountCommand command1 =
                new CreateUserAccountCommand(s1, "Mark");
        commandGateway.send(command1,(m,r)->{
            if (!r.isExceptional()) {
                log.info("創建物件成功");
                log.info("payload={}",r.getPayload());
            } else {
                log.info("oops, reason={}",r.exceptionResult().toString());
            }
        });
    }
}
