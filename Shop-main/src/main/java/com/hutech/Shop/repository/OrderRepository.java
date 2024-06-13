package com.hutech.Shop.repository;

import com.hutech.Shop.model.Order;
import com.hutech.Shop.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
