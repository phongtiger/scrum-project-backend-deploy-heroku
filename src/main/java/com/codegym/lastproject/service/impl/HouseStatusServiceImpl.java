package com.codegym.lastproject.service.impl;

import com.codegym.lastproject.model.HouseStatus;
import com.codegym.lastproject.model.util.StatusHouse;
import com.codegym.lastproject.repository.HouseStatusRepository;
import com.codegym.lastproject.repository.StatusRepository;
import com.codegym.lastproject.service.HouseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HouseStatusServiceImpl implements HouseStatusService {
    @Autowired
    private HouseStatusRepository houseStatusRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<HouseStatus> findAll() {
        return houseStatusRepository.findAll();
    }

    @Override
    public HouseStatus findById(Long id) {
        return houseStatusRepository.findById(id).get();
    }

    @Override
    public void save(HouseStatus houseStatus) {
        houseStatusRepository.save(houseStatus);
    }

    @Override
    public void deleteById(Long id) {
        houseStatusRepository.deleteById(id);
    }

    @Override
    public List<HouseStatus> findAllByHouseId(Long houseId) {
        return houseStatusRepository.findAllByHouseId(houseId);
    }

    @Override
    public HouseStatus findHouseStatusAvailable(Date beginDate, Date endDate, Long houseId) {
        return houseStatusRepository.findByBeginDateLessThanEqualAndEndDateGreaterThanEqualAndAndHouseIdAndStatus(beginDate, endDate, houseId, statusRepository.findByName(StatusHouse.AVAILABLE));
    }

    @Override
    public HouseStatus findHouseStatusBegin(Date beginDate, Long houseId) {
        return houseStatusRepository.findByBeginDateEqualsAndHouseIdAndStatus(beginDate, houseId, statusRepository.findByName(StatusHouse.AVAILABLE));
    }

    @Override
    public HouseStatus findHouseStatusEnd(Date endDate, Long houseId) {
        return houseStatusRepository.findByEndDateEqualsAndHouseIdAndStatus(endDate, houseId, statusRepository.findByName(StatusHouse.AVAILABLE));
    }

    @Override
    public HouseStatus findHouseStatusBooked(Date beginDate, Date endDate, Long houseId) {
        return houseStatusRepository.findByBeginDateEqualsAndEndDateEqualsAndHouseIdAndAndStatus(beginDate, endDate, houseId, statusRepository.findByName(StatusHouse.BOOKED));
    }
}
