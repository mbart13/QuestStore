package com.codecool.queststore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CourseDto {

    @NotBlank
    private String name;
}
