package com.systex.dddlab.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserAccountCommand {
    @TargetAggregateIdentifier
    private String userAccountId;
    private String name;
}
