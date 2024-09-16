package com.ris.swimticketing.dto;

import com.ris.swimticketing.model.TicketPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketOrderResponse {
    private Integer id;
    private BigDecimal ticketPrice;
    private Integer quantity;
    private BigDecimal totalPrice;
    private String customerName;
    private LocalDate ticketDate;
    private Instant orderDate;
}
