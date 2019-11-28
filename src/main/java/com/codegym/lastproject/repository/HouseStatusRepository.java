package com.codegym.lastproject.repository;

import com.codegym.lastproject.model.HouseStatus;
import com.codegym.lastproject.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HouseStatusRepository extends JpaRepository<HouseStatus, Long> {
    List<HouseStatus> findAllByHouseId (Long houseId);

    HouseStatus findByBeginDateLessThanEqualAndEndDateGreaterThanEqualAndAndHouseIdAndStatus
            (Date beginDate, Date endDate, Long houseId, Status status);
}
