package com.systex.dddlab;

import com.systex.dddlab.command.CreateUserAccountCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class Test5 {
    private static final Logger log = LoggerFactory.getLogger(Test5.class);
    @Autowired
    private CommandGateway commandGateway;

    @Test
    void testCreateUserAccount() {
        CreateUserAccountCommand command1 =
                new CreateUserAccountCommand(UUID.randomUUID().toString(), "Mark");
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
