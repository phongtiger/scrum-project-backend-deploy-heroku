package com.codegym.lastproject.service;

import com.codegym.lastproject.model.House;
import com.codegym.lastproject.model.User;

import java.util.List;

public interface HouseService {
    List<House> findAll();

    List<House> findByHostId(Long hostId);

    House findById(Long id);

    void save(House house);

    void deleteHouse(Long id);

    Long findMaxHouseId();

    boolean isHost(User user, House house);
}
