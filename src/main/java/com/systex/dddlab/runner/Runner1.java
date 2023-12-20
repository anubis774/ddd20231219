package com.systex.dddlab.runner;

import com.systex.dddlab.command.PlaceOrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.config.Configurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@Order(1)
public class Runner1 implements CommandLineRunner {
    @Autowired
    private Configurer configurer;
    @Autowired
    private CommandBus commandBus;

    @Override
    public void run(String... args) throws Exception {
        log.info("取得ddd axon的configurer={}", configurer);
        CommandBus cb = configurer.start().commandBus();
        log.info("取得command bus by configuration={}", cb);
        log.info("auto wire這個command bus={}", commandBus);
        //sendPlaceOrderCommand();
        sendPlaceOrderCommandAndGetReturn();
    }

    private void sendPlaceOrderCommandAndGetReturn() {
        // generate a command
        PlaceOrderCommand command1 = new PlaceOrderCommand(UUID.randomUUID().toString(), "Mark", 500);
        // command --> message
        CommandMessage<PlaceOrderCommand> message1 =
                GenericCommandMessage.asCommandMessage(command1);
        // dispatch
        commandBus.dispatch(message1, (message, result) -> {
            log.info("傳入的message是={}", message);
            if (!result.isExceptional()) {
                log.info("訊息正常發送");
                log.info("message payload={}",result.getPayload());
            } else {
                log.info("訊息不正確");
                log.info("exception訊息是:{}", result.exceptionResult().toString());
            }
        });
        log.info("傳送命令, 準備取得結果");

    }

    private void sendPlaceOrderCommand() {
        // generate a command
        PlaceOrderCommand command1 = new PlaceOrderCommand(UUID.randomUUID().toString(), "Mark", 500);
        // command --> message
        CommandMessage<PlaceOrderCommand> message =
                GenericCommandMessage.asCommandMessage(command1);
        // dispatch
        commandBus.dispatch(message);
        log.info("傳送命令完成");
    }
}
