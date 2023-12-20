package com.systex.dddlab.event;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserAccountCreatedEvent {
    private String id;
}
