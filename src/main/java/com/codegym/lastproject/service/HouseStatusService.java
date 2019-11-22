package com.codegym.lastproject.service;

import com.codegym.lastproject.model.HouseStatus;

import java.util.List;

public interface HouseStatusService {
    List<HouseStatus> findAll();

    HouseStatus findById(Long id);

    void save(HouseStatus houseStatus);

    void deleteById(Long id);

    List<HouseStatus> findAllByHouseId(Long houseId);
}
