package com.ris.swimticketing.repository;

import com.ris.swimticketing.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Integer> {
    @Query("SELECT COALESCE(SUM(t.quantity), 0) FROM TicketOrder t WHERE t.ticketDate = ?1")
    long totalQuantityByLocalDate(LocalDate ticketDate);
}