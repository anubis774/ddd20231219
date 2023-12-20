package com.systex.dddlab.handler;

import com.systex.dddlab.aggregate.UserAccount;
import com.systex.dddlab.command.CreateUserAccountCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserAccountEventHandler {
    private final Repository<UserAccount> repository;

    public UserAccountEventHandler(Repository<UserAccount> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateUserAccountCommand command) throws Exception {
        log.info("在command handler中");
        repository.newInstance(() -> {
            log.info("新產生{}的實例", command.getUserAccountId());
            return new UserAccount(command.getUserAccountId(), command.getName());
        });
        repository.load(command.getUserAccountId()).execute(userAccount -> {
                    log.info("把物件{}取出自己呼叫callback產生event", userAccount.getAccountId());
                    userAccount.afterCreateAccountCallback();
                }
        );
    }
}
