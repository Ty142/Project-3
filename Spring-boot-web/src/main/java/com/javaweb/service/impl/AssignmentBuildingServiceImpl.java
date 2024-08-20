package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    AssignmentBuildingRepository assignmentBuildingRepository;
    @Override
    public void deleteByBuildingIdIn(List<Long> ids) {
        assignmentBuildingRepository.deleteByBuildingIdIn(ids);
        }



}
