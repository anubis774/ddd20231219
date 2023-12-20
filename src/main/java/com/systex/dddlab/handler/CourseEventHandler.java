package com.systex.dddlab.handler;

import com.systex.dddlab.event.CourseBookedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CourseEventHandler {
    @EventHandler
    public void on(CourseBookedEvent event) {
        log.info("預約了課程, id是{}", event.getId());
    }
}
