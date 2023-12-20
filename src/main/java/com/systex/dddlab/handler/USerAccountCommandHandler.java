package com.systex.dddlab.handler;

import com.systex.dddlab.aggregate.UserAccount;
import com.systex.dddlab.command.CreateUserAccountCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class USerAccountCommandHandler {
    private final Repository<UserAccount> repository;

    public USerAccountCommandHandler(Repository<UserAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateUserAccountCommand command) throws Exception {
        log.info("在command handler中");
        repository.newInstance(() -> {
            log.info("創建一個aggregate");
            return new UserAccount(command.getUserAccountId(), command.getName());
        });
    }
}
