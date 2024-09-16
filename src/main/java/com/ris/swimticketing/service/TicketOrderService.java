package com.ris.swimticketing.service;

import com.ris.swimticketing.dto.TicketOrderRequest;
import com.ris.swimticketing.dto.TicketOrderResponse;

public interface TicketOrderService {
    TicketOrderResponse addTicketOrder(TicketOrderRequest ticketOrderRequest);
}
