package com.ris.swimticketing.controller;

import com.ris.swimticketing.dto.BaseResponse;
import com.ris.swimticketing.dto.TicketOrderRequest;
import com.ris.swimticketing.service.TicketOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/ticket-orders")
public class TicketOrderController {
    private TicketOrderService ticketOrderService;

    @Autowired
    public TicketOrderController(TicketOrderService ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addTicketOrder(@RequestBody TicketOrderRequest ticketOrderRequest) {
        log.info("masuk");
        Object response = ticketOrderService.addTicketOrder(ticketOrderRequest);
        return ResponseEntity.ok(BaseResponse.success(response, "Ticket order successfully", HttpStatus.OK.value()));
    }
}
