package com.systex.dddlab.handler;

import com.systex.dddlab.aggregate.UserAccount;
import com.systex.dddlab.command.CreateUserAccountCommand;
import com.systex.dddlab.event.UserAccountCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserAccountEventHandler {
    @EventHandler
    public void handle(UserAccountCreatedEvent event) {
        log.info("帳號:{}已經被創建了", event.getId());
        // 存DB, RESTFul, LDAP...
    }
}
