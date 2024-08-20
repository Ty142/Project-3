package com.javaweb.converter;

import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.utils.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;



    @Component
    public class BuildingSearchBuilderConverter {
        public BuildingSearchRequest toBuildingSearchRequest(Map<String, Object> params, List<String> typeCode) {
            BuildingSearchRequest.Request builder = new BuildingSearchRequest.Request()

                    .setName(MapUtils.getObject(params,"name", String.class))

                    .setFloorArea(MapUtils.getObject(params,"floorArea", Long.class))

                    .setDistrict(MapUtils.getObject(params,"district", String.class))

                    .setWard(MapUtils.getObject(params,"ward", String.class))

                    .setStreet(MapUtils.getObject(params,"street", String.class))

                    .setNumberOfBasement(MapUtils.getObject(params,"numberOfBasement", Long.class))

                    .setDirection(MapUtils.getObject(params,"direction", String.class))

                    .setLevel(MapUtils.getObject(params,"level", Long.class))

                    .setAreaFrom(MapUtils.getObject(params,"areaFrom", Long.class))

                    .setAreaTo(MapUtils.getObject(params,"areaTo", Long.class))

                    .setRentPriceFrom(MapUtils.getObject(params,"rentPriceFrom", Long.class))

                    .setRentPriceTo(MapUtils.getObject(params,"rentPriceTo", Long.class))

                    .setManagerName(MapUtils.getObject(params,"managerName", String.class))

                    .setManagerPhone(MapUtils.getObject(params,"managerPhone", String.class))

                    .setStaffId(MapUtils.getObject(params,"staffId", Long.class))

                    .setTypeCode(typeCode);

            BuildingSearchRequest request = builder.request();

            return request;

        }
}
