package com.upthemuscle.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoResponse {

    private String name;
    private String lastname;
    private String telephone;
    private String address;
    private String documentType;
    private String documentNumber;
    private String emergencyNumber;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String medicalHistory;

    private String photo;

    //private String dateStart;

    //private String dateFinish;

    private Long missingDays;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setEmergencyNumber(String emergencyNumber) {
        this.emergencyNumber = emergencyNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Long getMissingDays() {

        Long days = -1L;

        try {
            days = ChronoUnit.DAYS.between(this.startDate, this.finishDate);
        } catch (Exception e) {
            System.out.println("No pasa nada rey");
        }

        return days;

    }

    public String getMedicalHistory() {
        return this.medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /*
    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }
    */

    @Override
    public String toString() {
        return "ClientDto{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\''  +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", emergencyNumber='" + emergencyNumber + '\'' +
                '}';
    }
}
