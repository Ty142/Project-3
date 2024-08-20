package com.javaweb.api.admin;


import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @PostMapping
    public ResponseDTO addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        ResponseDTO responseDTO = buildingService.addOrUpdateBuilding(buildingDTO);
        return responseDTO;
    }

    @DeleteMapping("/{ids}")
    public void deleteBuildings(@PathVariable List<Long> ids){
         buildingService.deleteBuildingById(ids);
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO responseDTO = buildingService.findStaffsByBuildingId(id);
        return responseDTO;
    }
    @PostMapping("staffs")
    public ResponseDTO updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        ResponseDTO responseDTO = buildingService.UpdateBuildingAssignment(assignmentBuildingDTO);
        return responseDTO;
    }
}