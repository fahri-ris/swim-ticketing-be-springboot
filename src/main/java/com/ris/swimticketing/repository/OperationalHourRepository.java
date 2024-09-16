package com.ris.swimticketing.repository;

import com.ris.swimticketing.model.OperationalHour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationalHourRepository extends JpaRepository<OperationalHour, Integer> {
}