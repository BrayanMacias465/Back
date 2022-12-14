package com.upthemuscle.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CoachDto {

    @NotBlank
    private String name;
    private String lastname;
    private String telephone;
    private String address;
    private String documentType;
    private String documentNumber;
    private String email;
    private float salary;
    private String photo;

}
