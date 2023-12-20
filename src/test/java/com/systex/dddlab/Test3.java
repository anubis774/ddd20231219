package com.systex.dddlab;


import com.systex.dddlab.command.PlaceOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;


@SpringBootTest
public class Test3 {
    private static final Logger log = LoggerFactory.getLogger(Test.class);
    @Autowired
    private CommandGateway commandGateway;

    @Test
    public void sendViaGateway() {
        PlaceOrderCommand command1 = new PlaceOrderCommand(UUID.randomUUID().toString(),
                "Kevin", 30);
        PlaceOrderCommand command2 = new PlaceOrderCommand(UUID.randomUUID().toString(),
                "Kevin2", 300);
        log.info("send {}", command1);
        commandGateway.send(command1,(command,result)->{

            log.info("這裡才是執行完command1的地方,cmd={}",command);
            log.info("command1的結果是否異常?{}",result.isExceptional());
        });
        log.info("send {}", command2);
        commandGateway.send(command2,(command,result)->{

            log.info("這裡才是執行完command2的地方,cmd={}",command);
            log.info("command2的結果是否異常?{}",result.isExceptional());
        });
        log.info("finish send");
    }
    @Test
    public void sendViaGatewayByOrder() {
        PlaceOrderCommand command1 = new PlaceOrderCommand(UUID.randomUUID().toString(),
                "Kevin", 30);
        PlaceOrderCommand command2 = new PlaceOrderCommand(UUID.randomUUID().toString(),
                "Kevin2", 300);
        commandGateway.send(command1,(rcmd1,result1)->{
            if (!result1.isExceptional()) { // success
                log.info("command1執行正確,detail={}",rcmd1);
                commandGateway.send(command2, (rcmd2, result2) -> {
                    if (!result2.isExceptional()) {
                        log.info("command2執行正確,detail={}",rcmd2);
                    }
                });

            }
        });

    }

    @Test
    public void sendViaGatewaySync() {
        PlaceOrderCommand command1 = new PlaceOrderCommand(UUID.randomUUID().toString(),
                "Kevin", 30);
        PlaceOrderCommand command2 = new PlaceOrderCommand(UUID.randomUUID().toString(),
                "Kevin2", 300);
        Object o1 = commandGateway.sendAndWait(command1);
        log.info("o1={}",o1);
        Object o2 = commandGateway.sendAndWait(command2);
        log.info("o2={}",o2);
    }
}
