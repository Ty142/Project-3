package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingSearchResponse> findAll(Map<String, Object> params, List<String> typeOfCode);
    ResponseDTO addOrUpdateBuilding(BuildingDTO buildingDTO);
    void deleteBuildingById(List<Long> ids);
    ResponseDTO findStaffsByBuildingId(Long buildingId);
    BuildingDTO findBuildingById(Long id);
    ResponseDTO UpdateBuildingAssignment(AssignmentBuildingDTO assignmentBuildingDTO);
}