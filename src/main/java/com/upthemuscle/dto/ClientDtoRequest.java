package com.upthemuscle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDtoRequest {
    @NotBlank
    private String name;
    private String lastname;
    private String telephone;
    private String address;
    private String documentType;
    private String documentNumber;
    private String emergencyNumber;

    private String startDate;

    private String finishDate;

    private String medicalHistory;

    private String photo;

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", emergencyNumber='" + emergencyNumber + '\'' +
                '}';
    }
}
