package com.ris.swimticketing.service;

import com.ris.swimticketing.dto.TicketOrderRequest;
import com.ris.swimticketing.dto.TicketOrderResponse;
import com.ris.swimticketing.model.TicketOrder;
import com.ris.swimticketing.model.TicketPrice;
import com.ris.swimticketing.repository.TicketOrderRepository;
import com.ris.swimticketing.repository.TicketPriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;

@Slf4j
@Service
public class TicketOrderServiceImpl implements TicketOrderService {
    private final TicketOrderRepository ticketOrderRepository;
    private final TicketPriceRepository ticketPriceRepository;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository, TicketPriceRepository ticketPriceRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
        this.ticketPriceRepository = ticketPriceRepository;
    }

    @Override
    public TicketOrderResponse addTicketOrder(TicketOrderRequest ticketOrderRequest) {
        try {
            // check stock & validation quantity
            log.info("masuk service");
            long totalStock = 100;
            long totalOrders = ticketOrderRepository.totalQuantityByLocalDate(ticketOrderRequest.getTicketDate());

            long currentStock = totalStock - totalOrders;

            if (ticketOrderRequest.getQuantity() > currentStock) {
                throw new BadRequestException("Stock not enough. Current stock is " + currentStock);
            }

            // total price
            int dayNumber = ticketOrderRequest.getTicketDate().getDayOfWeek().getValue();
            TicketPrice ticketPrice = ticketPriceRepository.findByOperationalHoursId(dayNumber);
            BigDecimal totalPrice = ticketPrice.getPrice().multiply(BigDecimal.valueOf(ticketOrderRequest.getQuantity()));

            // create new order
            TicketOrder newOrder = TicketOrder.builder()
                    .ticketPrice(ticketPrice)
                    .totalPrice(totalPrice)
                    .customerName(ticketOrderRequest.getCustomerName())
                    .ticketDate(ticketOrderRequest.getTicketDate())
                    .orderDate(Instant.now())
                    .quantity(ticketOrderRequest.getQuantity())
                    .build();
            TicketOrder savedOrder = ticketOrderRepository.save(newOrder);

            log.info("mau return");

            return TicketOrderResponse.builder()
                    .id(savedOrder.getId())
                    .ticketPrice(savedOrder.getTicketPrice().getPrice())
                    .quantity(savedOrder.getQuantity())
                    .totalPrice(savedOrder.getTotalPrice())
                    .customerName(savedOrder.getCustomerName())
                    .ticketDate(savedOrder.getTicketDate())
                    .orderDate(Instant.now())
                    .build();
        } catch (BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
