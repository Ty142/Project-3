package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingCustomRepository {
    void deleteAllByIdIn(List<Long> buildingIds);

}