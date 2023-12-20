package com.systex.dddlab;

import com.systex.dddlab.aggregate.Course;
import com.systex.dddlab.command.CreateCourseCommand;
import com.systex.dddlab.event.CourseBookedEvent;
import com.systex.dddlab.event.CourseCreatedEvent;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@SpringBootTest
public class Test6 {
    private FixtureConfiguration<Course> fixture;

    @BeforeEach
    public void setupFixture() {
        fixture = new AggregateTestFixture<>(Course.class);
    }

    @Test
    public void fixtureNotNull() {
        Assertions.assertNotNull(fixture);
    }

    @Test
    public void testCourseCreatedEvent() {
        String uuid = UUID.randomUUID().toString();
        CreateCourseCommand command = new CreateCourseCommand(uuid, "DDD & Spring boot");
        fixture.givenNoPriorActivity().when(command)
                .expectEvents(new CourseCreatedEvent(uuid));
    }

    @Test
    @Disabled("wrong case1")
    public void testCourseCreatedUUIDWillBeTheSame() {
        String uuid = UUID.randomUUID().toString();
        CreateCourseCommand command = new CreateCourseCommand(uuid, "DDD & Spring boot");
        fixture.givenNoPriorActivity().when(command)
                .expectEvents(new CourseCreatedEvent(UUID.randomUUID().toString()));
    }

    @Test
    public void testCourseCreatedEventExpected() {
        String uuid = UUID.randomUUID().toString();
        CreateCourseCommand command = new CreateCourseCommand(uuid, "DDD & Spring boot");
        fixture.givenNoPriorActivity().when(command)
                .expectEvents(new CourseBookedEvent(uuid));
    }
}