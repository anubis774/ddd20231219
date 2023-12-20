package com.systex.dddlab;

import com.systex.dddlab.command.PlaceOrderCommand;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class Test2 {
    private static final Logger log = LoggerFactory.getLogger(Test.class);
    @Autowired
    private CommandBus commandBus;

    @Test
    public void placeOrderCommandSingleShot() {
        PlaceOrderCommand command1 = new PlaceOrderCommand(UUID.randomUUID().toString(), "Mark", 500);
        // command --> message
        CommandMessage<PlaceOrderCommand> message1 =
                GenericCommandMessage.asCommandMessage(command1);
        // dispatch
        commandBus.dispatch(message1, (message, result) -> {
            log.info("傳入的message是={}", message);
            if (!result.isExceptional()) {
                log.info("訊息正常發送");
                log.info("message payload={}", result.getPayload());
            } else {
                log.info("訊息不正確");
                log.info("exception訊息是:{}", result.exceptionResult().toString());
            }
        });

        log.info("傳送命令, 準備取得結果");
    }
    @Test
    @Disabled("bad example")
    public void placeOrderCommandDoubleShot() {
        PlaceOrderCommand command1 = new PlaceOrderCommand(UUID.randomUUID().toString(), "Kevin", 300);
        // command --> message
        CommandMessage<PlaceOrderCommand> message1 =
                GenericCommandMessage.asCommandMessage(command1);
        // dispatch
        commandBus.dispatch(message1, (message, result) -> {
            log.info("傳入的message是={}", message);
            if (!result.isExceptional()) {
                log.info("訊息正常發送");
                log.info("message payload={}", result.getPayload());
            } else {
                log.info("訊息不正確");
                log.info("exception訊息是:{}", result.exceptionResult().toString());
            }
        });
        commandBus.dispatch(message1, (message, result) -> {
            log.info("傳入的message是={}", message);
            if (!result.isExceptional()) {
                log.info("訊息正常發送");
                log.info("message payload={}", result.getPayload());
            } else {
                log.info("訊息不正確");
                log.info("exception訊息是:{}", result.exceptionResult().toString());
            }
        });

        log.info("傳送命令, 準備取得結果");
    }
}
