package com.hutech.Shop.repository;

import com.hutech.Shop.model.Order;
import com.hutech.Shop.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
