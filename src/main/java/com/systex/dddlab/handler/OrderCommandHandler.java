package com.systex.dddlab.handler;


import com.systex.dddlab.command.PlaceOrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderCommandHandler {
    @CommandHandler
    public void on(PlaceOrderCommand command) {
        log.info("接收到place order command={}", command);
    }
}
