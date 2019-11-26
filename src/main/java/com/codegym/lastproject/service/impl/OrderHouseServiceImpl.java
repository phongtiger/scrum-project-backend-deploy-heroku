package com.codegym.lastproject.service.impl;

import com.codegym.lastproject.repository.OrderHouseRepository;
import com.codegym.lastproject.service.OrderHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderHouseServiceImpl implements OrderHouseService {
    @Autowired
    private OrderHouseRepository orderHouseRepository;
}
