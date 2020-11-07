package com.codecool.queststore.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RankDto {

    @NotBlank(message = "Field cannot be blank")
    private String name;

    private int requiredCurrency;
}
