package com.ris.swimticketing.repository;

import com.ris.swimticketing.model.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPriceRepository extends JpaRepository<TicketPrice, Integer> {
    TicketPrice findByOperationalHoursId(int dayNumber);
}