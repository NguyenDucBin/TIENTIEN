package com.example.Evara_Shop.model;

import com.example.Evara_Shop.enums.OrderStatus;
import com.example.Evara_Shop.state.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Convert(converter = OrderStatusConverter.class)
    @Column(length = 20)
    private OrderStatus status;

    @Transient
    private OrderState state;

    @PostLoad
    public void initState() {
        switch (status) {
            case PENDING -> state = new CreatedState();
            case PROCESSING -> state = new PaidState();
            case COMPLETED -> state = new DeliveredState();
            case CANCELLED -> state = new CancelledState();
        }
    }

    public void nextState() { state.next(this); }

    public void cancel() { state.cancel(this); }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}

