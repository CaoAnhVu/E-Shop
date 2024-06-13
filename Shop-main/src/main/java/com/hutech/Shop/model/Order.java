package com.hutech.Shop.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}