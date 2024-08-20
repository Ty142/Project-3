package com.javaweb.model.request;

import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuildingSearchRequest extends AbstractDTO {
    private String name;
    private Long floorArea;
    private String district;
    private String ward;
    private String street;
    private Long numberOfBasement;
    private String direction;
    private Long level;
    private Long areaFrom;
    private Long areaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private List<String> typeCode;

    public BuildingSearchRequest() {
    }

    private BuildingSearchRequest(Request request) {
        this.name = request.name;
        this.floorArea = request.floorArea;
        this.district = request.district;
        this.ward = request.ward;
        this.street = request.street;
        this.numberOfBasement = request.numberOfBasement;
        this.direction = request.direction;
        this.level = request.level;
        this.areaFrom = request.areaFrom;
        this.areaTo = request.areaTo;
        this.rentPriceFrom = request.rentPriceFrom;
        this.rentPriceTo = request.rentPriceTo;
        this.managerName = request.managerName;
        this.managerPhone = request.managerPhone;
        this.staffId = request.staffId;
        this.typeCode = request.typeCode;
    }

    public static class Request {
        private String name;
        private Long floorArea;
        private String district;
        private String ward;
        private String street;
        private Long numberOfBasement;
        private String direction;
        private Long level;
        private Long areaFrom;
        private Long areaTo;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private String managerName;
        private String managerPhone;
        private Long staffId;
        private List<String> typeCode;

        public Request setName(String name) {
            this.name = name;
            return this;
        }

        public Request setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Request setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Request setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Request setStreet(String street) {
            this.street = street;
            return this;
        }

        public Request setNumberOfBasement(Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Request setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Request setLevel(Long level) {
            this.level = level;
            return this;
        }

        public Request setAreaFrom(Long areaFrom) {
            this.areaFrom = areaFrom;
            return this;
        }

        public Request setAreaTo(Long areaTo) {
            this.areaTo = areaTo;
            return this;
        }

        public Request setRentPriceFrom(Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Request setRentPriceTo(Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Request setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Request setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }

        public Request setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Request setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public BuildingSearchRequest request() {
            return new BuildingSearchRequest(this);
        }
    }
}
