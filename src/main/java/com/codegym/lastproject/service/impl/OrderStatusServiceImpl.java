package com.codegym.lastproject.service.impl;

import com.codegym.lastproject.model.OrderStatus;
import com.codegym.lastproject.model.util.StatusOrder;
import com.codegym.lastproject.repository.OrderStatusRepository;
import com.codegym.lastproject.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public OrderStatus findByStatus(StatusOrder statusOrder) {
        return orderStatusRepository.findByName(statusOrder);
    }

    @Override
    public void save(OrderStatus orderStatus) {
        orderStatusRepository.save(orderStatus);
    }
}
