package com.codegym.lastproject.repository;

import com.codegym.lastproject.model.HouseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseStatusRepository extends JpaRepository<HouseStatus, Long> {
    List<HouseStatus> findAllByHouseId (Long houseId);
}
