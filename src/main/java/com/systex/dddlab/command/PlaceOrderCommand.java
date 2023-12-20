package com.systex.dddlab.command;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@AllArgsConstructor
public class PlaceOrderCommand {
    @TargetAggregateIdentifier
    private final String id;
    private String userName;
    private long amount;

}
