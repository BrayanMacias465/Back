package com.upthemuscle.service;

import com.upthemuscle.dto.ClientDtoResponse;
import com.upthemuscle.dto.CoachDto;

import java.util.List;

public interface ICoachService {

    CoachDto saveCoach(CoachDto coachDto);

    List<CoachDto> listCoach();

    CoachDto getCoach(String documentNumber);

    void deleteByDocumentNumber(String documentNumber);

    Boolean existsByDocumentNumber(String documentNumber);

}
