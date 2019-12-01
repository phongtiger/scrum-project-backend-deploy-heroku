package com.codegym.lastproject.repository;

import com.codegym.lastproject.model.OrderStatus;
import com.codegym.lastproject.model.util.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    OrderStatus findByName(StatusOrder statusOrder);
}
