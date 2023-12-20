package com.systex.dddlab.event;

import lombok.Data;
import lombok.Value;

@Value
@Data
public class CourseCreatedEvent {
    private String courseId;

    public CourseCreatedEvent(String courseId) {
        this.courseId = courseId;
    }
}
