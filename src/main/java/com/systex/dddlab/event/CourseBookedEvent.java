package com.systex.dddlab.event;

import lombok.Value;

@Value
public class CourseBookedEvent {
    private final String id;
}

