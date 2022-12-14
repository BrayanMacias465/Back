package com.upthemuscle.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @Column(name = "person_id")
    private Long id;

    private String emergencyNumber;

    private String medicalHistory;

    @DateTimeFormat(pattern = "d-M-yyyy")
    @JsonFormat(pattern = "d-M-yyyy", timezone = "GMT+8")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "d-M-yyyy")
    @JsonFormat(pattern = "d-M-yyyy", timezone = "GMT+8")
    private LocalDate finishDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "person_id")
    private Person person;

}
