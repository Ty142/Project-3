package com.javaweb.service.impl;



import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingDTOConverter buildingDTOConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentAreaService rentAreaService;

    @Autowired
    AssignmentBuildingService assignmentBuildingService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired private AssignmentBuildingRepository assignmentBuildingRepository;


    @Override
    public List<BuildingSearchResponse> findAll(Map<String, Object> params, List<String> typeOfCode) {
        BuildingSearchRequest buildingSearchRequest = buildingSearchBuilderConverter.toBuildingSearchRequest(params, typeOfCode);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities) {
            result.add(buildingDTOConverter.toBuildingRespone(buildingEntity));
        }
        return result;
    }

    @Override
    public ResponseDTO addOrUpdateBuilding(BuildingDTO buildingDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        if(buildingEntity.getId() != null) {
            responseDTO.setMessage("update success");
        } else responseDTO.setMessage("create success");
        buildingRepository.save(buildingEntity);
        rentAreaService.addRentArea(buildingDTO);
        return responseDTO;
    }

    @Override
    public void deleteBuildingById(List<Long> ids) {
        rentAreaService.deleteByIdIn(ids);
        assignmentBuildingService.deleteByBuildingIdIn(ids);
        for(Long id : ids) {
            buildingRepository.deleteById(id);
        }
    }

    @Override
    public ResponseDTO findStaffsByBuildingId(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> assignedStaffList = buildingEntity.getAssignmentBuildingEntityList().stream().map(AssignmentBuildingEntity::getStaffs).collect(Collectors.toList());
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for(UserEntity u : staffList){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setStaffId(u.getId());
            staffResponseDTO.setFullName(u.getFullName());
            if(assignedStaffList.contains(u)){
                staffResponseDTO.setChecked("checked");
            }
            else staffResponseDTO.setChecked("");
            staffResponseDTOS.add(staffResponseDTO);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }


    @Override
    public BuildingDTO findBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        return buildingDTOConverter.toBuildingDTO(buildingEntity);
    }

    @Override
    public ResponseDTO UpdateBuildingAssignment(AssignmentBuildingDTO assignmentBuildingDTO) {
        List<Long> staffIds = assignmentBuildingDTO.getStaffs();
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();

        if (buildingEntity == null) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Building not found");
            return responseDTO;
        }
        assignmentBuildingRepository.deleteByBuildingId(buildingEntity.getId());
        List<AssignmentBuildingEntity> assignments = new ArrayList<>();
            for (Long id : staffIds) {
            UserEntity userEntity = userRepository.findById(id).get();
            if (userEntity != null) {
                AssignmentBuildingEntity assignment = new AssignmentBuildingEntity();
                assignment.setBuilding(buildingEntity);
                assignment.setStaffs(userEntity);
                assignments.add(assignment);
            }
        }

        assignmentBuildingRepository.saveAll(assignments);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        return responseDTO;
    }



}

