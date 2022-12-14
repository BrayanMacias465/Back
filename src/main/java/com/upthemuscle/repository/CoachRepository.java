package com.upthemuscle.repository;

import com.upthemuscle.model.Client;
import com.upthemuscle.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    Coach findByPerson_id(Long id);

}
