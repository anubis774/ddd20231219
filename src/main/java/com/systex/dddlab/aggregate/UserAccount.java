package com.systex.dddlab.aggregate;

import com.systex.dddlab.event.UserAccountCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor // important!!!
@AllArgsConstructor
@Slf4j
@Data
public class UserAccount {
    @AggregateIdentifier
    private String accountId;
    private String name;

    public void afterCreateAccountCallback() {
        AggregateLifecycle.apply(new UserAccountCreatedEvent(accountId));
    }
}
