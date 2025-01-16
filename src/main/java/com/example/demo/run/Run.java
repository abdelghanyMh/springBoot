package com.example.demo.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run( Integer id,
                   @NotEmpty(message = "Title cannot be empty")
                   String title,
                   LocalDateTime startedOn,
                   LocalDateTime completedOn,
                   @Positive
                   Integer miles,
                   Location location) {
    public Run{
        if(!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("completed On must be after started on");
        }
    }
}
