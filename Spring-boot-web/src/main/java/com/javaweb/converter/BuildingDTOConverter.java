
package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.utils.DistrictCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingDTOConverter {
    @Autowired
    public ModelMapper modelMapper;

    @Autowired RentAreaConverter rentAreaConverter;

    public BuildingSearchResponse toBuildingRespone(BuildingEntity buildingEntity){
        BuildingSearchResponse building = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        building.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + buildingEntity.getDistrict());
        List<RentAreaEntity> rentAreas = buildingEntity.getRentareaEntityList();
        if(rentAreas != null){
            String rentArea = rentAreas.stream()
                    .map(RentAreaEntity::getValue)
                    .map(String::valueOf) // Chuyển đổi giá trị thành String nếu cần
                    .collect(Collectors.joining(", "));
            building.setRentArea(rentArea);
        }

        Map<String,String> districts = DistrictCode.type();
        String dictrictName = "";
        if(buildingEntity.getDistrict()!= null && !buildingEntity.getDistrict().isEmpty()) {
            dictrictName = districts.get(buildingEntity.getDistrict());
        }
        if(dictrictName != null && !dictrictName.isEmpty()) {
            building.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + dictrictName);
        }
        return building;
    }

    public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        String type = buildingDTO.getTypeCode().stream().map(it->it.toString()).collect(Collectors.joining(", "));
        buildingEntity.setType(type);
        buildingEntity.setRentareaEntityList(rentAreaConverter.listRentArea(buildingDTO,buildingEntity));
        return buildingEntity;
    }

    public BuildingDTO toBuildingDTO(BuildingEntity buildingEntity){
        BuildingDTO building = modelMapper.map(buildingEntity, BuildingDTO.class);
        building.setDistrict(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + buildingEntity.getDistrict());
        List<RentAreaEntity> rentAreas = buildingEntity.getRentareaEntityList();
        List<String> typeCodes = new ArrayList<>();
        if(buildingEntity.getType() != null){
            String[] typeCode = buildingEntity.getType().split(",");
            for(String type : typeCode){
                typeCodes.add(type);
            }
        }
        building.setTypeCode(typeCodes);
        if(rentAreas != null){
            String rentArea = rentAreas.stream().map(it -> String.valueOf(it.getValue())).collect(Collectors.joining(", "));
            building.setRentArea(rentArea);
        }
        return building;
    }

    public BuildingSearchResponse toBuildingSearchResponse(BuildingDTO buildingDTO){
        BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
        buildingSearchResponse.setAddress(buildingDTO.getStreet() + ", " + buildingDTO.getWard() + ", " + buildingDTO.getDistrict());
        buildingSearchResponse.setRentArea(buildingDTO.getRentArea());
        return buildingSearchResponse;
    }
}
