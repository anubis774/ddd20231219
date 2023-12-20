package com.systex.dddlab.handler;

import com.systex.dddlab.command.UsePointCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UsePointCommandHandler {
    @CommandHandler
    public void on(UsePointCommand command) {
        log.info("{}使用了{}點", command.getUsername(), command.getAmount());
    }
}