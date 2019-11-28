package com.codegym.lastproject.repository;

import com.codegym.lastproject.model.HouseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseStatusRepository extends JpaRepository<HouseStatus, Long> {
    List<HouseStatus> findAllByHouseId (Long houseId);

//    @Query("select s from HouseStatus s where s.id = :houseId")
//    List<HouseStatus> findByHouseId (@Param("hId") Long hId);
}
