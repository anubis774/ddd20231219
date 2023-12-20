package com.systex.dddlab.runner;

import com.systex.dddlab.event.CourseBookedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@Order(6)
public class Runner6 implements CommandLineRunner {
    @Autowired
    private EventGateway eventGateway;

    @Override
    public void run(String... args) throws Exception {
        CourseBookedEvent e1 = new CourseBookedEvent(UUID.randomUUID().toString());
        eventGateway.publish(e1);
    }
}
