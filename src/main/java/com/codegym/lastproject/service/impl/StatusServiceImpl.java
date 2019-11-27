package com.codegym.lastproject.service.impl;

import com.codegym.lastproject.model.Status;
import com.codegym.lastproject.model.util.StatusHouse;
import com.codegym.lastproject.repository.StatusRepository;
import com.codegym.lastproject.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status findByStatus(StatusHouse statusHouse) {
        return statusRepository.findByStatus(statusHouse);
    }

    @Override
    public void save(Status status) {
        statusRepository.save(status);
    }
}
