package com.javaweb.service.impl;



import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
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
    private ModelMapper modelMapper;

    @Autowired private RentAreaRepository rentAreaRepository;

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
        BuildingEntity buildingEntity = buildingDTOConverter.toBuildingEntity(buildingDTO);
        rentAreaRepository.deleteByBuildingId(buildingEntity.getId());
        if(buildingEntity.getId() != null) {
            responseDTO.setMessage("update success");
        } else responseDTO.setMessage("create success");
        buildingRepository.save(buildingEntity);
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentareaEntityList();
        rentAreaRepository.saveAll(rentAreaEntities);
        return responseDTO;
    }

    @Override
    public void deleteBuildingById(List<Long> ids) {
        rentAreaRepository.deleteByBuilding_IdIn(ids);
        for(Long id : ids) {
            buildingRepository.deleteById(id);
        }
    }

    @Override
    public ResponseDTO findStaffsByBuildingId(Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffList = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> assignedStaffList = buildingEntity.getUsers();
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
        List<UserEntity> userEntities = new ArrayList<>();
        if (buildingEntity == null) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Building not found");
            return responseDTO;
        }
        for (Long staffId : staffIds) {
            userEntities.add(userRepository.findById(staffId).get());
        }
        buildingEntity.setUsers(userEntities);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("success");
        return responseDTO;
    }





}

