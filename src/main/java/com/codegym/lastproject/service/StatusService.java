package com.codegym.lastproject.service;

import com.codegym.lastproject.model.Status;
import com.codegym.lastproject.model.util.StatusHouse;

public interface StatusService {
    Status findByStatus(StatusHouse statusHouse);

    void save(Status status);}
