package com.systex.dddlab.command;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.axonframework.commandhandling.RoutingKey;

@Value
@AllArgsConstructor
public class UsePointCommand {
    @RoutingKey
    private final String id;
    private String username;
    private int amount;
}
