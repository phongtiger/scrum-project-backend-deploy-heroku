package com.codegym.lastproject.service.impl;

import com.codegym.lastproject.model.OrderHouse;
import com.codegym.lastproject.model.util.StatusOrder;
import com.codegym.lastproject.repository.OrderHouseRepository;
import com.codegym.lastproject.service.OrderHouseService;
import com.codegym.lastproject.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHouseServiceImpl implements OrderHouseService {
    @Autowired
    private OrderHouseRepository orderHouseRepository;

    @Autowired
    private OrderStatusService orderStatusService;

    @Override
    public List<OrderHouse> findAll() {
        return orderHouseRepository.findAll();
    }

    @Override
    public OrderHouse findById(Long id) {
        return orderHouseRepository.findById(id).get();
    }

    @Override
    public void saveOrder(OrderHouse orderHouse) {
        orderHouseRepository.save(orderHouse);
    }

    @Override
    public void deleteOrder(Long id) {
        orderHouseRepository.deleteById(id);
    }

    @Override
    public List<OrderHouse> findByHouseId(Long houseId) {
        return orderHouseRepository.findByHouseId(houseId);
    }

    @Override
    public List<OrderHouse> findByTenantId(Long tenantId) {
        return orderHouseRepository.findByTenantId(tenantId);
    }

    @Override
    public List<OrderHouse> findProcessingOrderByHouseId(Long houseId) {
        return orderHouseRepository.findByHouseIdAndOrderStatus(houseId, orderStatusService.findByStatus(StatusOrder.PROCESSING));
    }
}
