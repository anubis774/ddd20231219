package com.systex.dddlab.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateCourseCommand {
    @TargetAggregateIdentifier
    private String courseId;
    private String name;

    public CreateCourseCommand(String courseId) {
        this.courseId = courseId;
    }
}
