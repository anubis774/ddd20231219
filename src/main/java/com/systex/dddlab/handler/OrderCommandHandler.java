package com.systex.dddlab.handler;


import com.systex.dddlab.command.PlaceOrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderCommandHandler {
    @CommandHandler
    public void handle(PlaceOrderCommand command) {
        log.info("接收到place order command={}", command);
    }
//    @CommandHandler
//    public void handleMore(PlaceOrderCommand cmd) {
//        log.info("另一個={}",cmd);
//    }
}
