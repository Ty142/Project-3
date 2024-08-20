package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;

import java.util.List;

public interface AssignmentBuildingService {
    void deleteByBuildingIdIn(List<Long> ids);
}
