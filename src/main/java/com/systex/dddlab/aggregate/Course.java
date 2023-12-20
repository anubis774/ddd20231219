package com.systex.dddlab.aggregate;

import com.systex.dddlab.command.CreateCourseCommand;
import com.systex.dddlab.event.CourseCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @AggregateIdentifier
    private String id;

    @CommandHandler
    public Course(CreateCourseCommand command) {
        log.info("生成課程id是{}", command.getCourseId());
        AggregateLifecycle.apply(new CourseCreatedEvent(command.getCourseId()));
    }

    @EventSourcingHandler
    public void on(CourseCreatedEvent event) {
        // initialization
        id = event.getCourseId(); // very very very important!!!!
    }
}
