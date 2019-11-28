package com.codegym.lastproject.service;

import com.codegym.lastproject.model.OrderHouse;

import java.util.List;

public interface OrderHouseService {
    List<OrderHouse> findAll();

    OrderHouse findById(Long id);

    void saveOrder(OrderHouse orderHouse);

    void deleteOrder(Long id);

    List<OrderHouse> findByHouseId(Long houseId);

    List<OrderHouse> findByTenantId(Long tenantId);
}
