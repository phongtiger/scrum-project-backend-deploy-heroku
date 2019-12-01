package com.codegym.lastproject.service;

import com.codegym.lastproject.model.OrderStatus;
import com.codegym.lastproject.model.util.StatusOrder;

public interface OrderStatusService {
    OrderStatus findByStatus(StatusOrder statusOrder);

    void save(OrderStatus orderStatus);
}
