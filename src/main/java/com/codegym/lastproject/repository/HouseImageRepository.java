package com.codegym.lastproject.repository;

import com.codegym.lastproject.model.HouseImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseImageRepository extends JpaRepository<HouseImage, Long> {
}
