package com.codegym.lastproject.repository;

import com.codegym.lastproject.model.Status;
import com.codegym.lastproject.model.util.StatusHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByStatus(StatusHouse statusHouse);
}
