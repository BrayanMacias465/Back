package com.upthemuscle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private String name;
    private String lastname;
    private String email;
    private String telephone;
    private String address;
    private String documentType;
    private String documentNumber;

}
