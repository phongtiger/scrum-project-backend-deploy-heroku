package com.codegym.lastproject.repository;

import com.codegym.lastproject.model.OrderHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHouseRepository extends JpaRepository<OrderHouse, Long> {
}
