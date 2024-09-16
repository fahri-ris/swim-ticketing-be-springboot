package com.ris.swimticketing.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket_orders")
@Builder
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ColumnDefault("nextval('ticket_orders_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_price_id")
    private TicketPrice ticketPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "ticket_date")
    private LocalDate ticketDate;

    @Column(name = "order_date")
    private Instant orderDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_by")
    private Integer createdBy;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_by")
    private Integer deletedBy;

    @Column(name = "deleted_at")
    private Instant deletedAt;

}