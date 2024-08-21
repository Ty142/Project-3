package com.javaweb.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum district {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_10("Quận 10"),
    QUAN_11("Quận 11"),
    QUAN_TB("Quận Tân Bình");

    private final String districtName;
    district(String name){
        this.districtName = name;
    }

    public String getDistrictName() {
        return districtName;
    }

    public static Map<String, String> districtCode(){
        Map<String, String> code = new LinkedHashMap<>();
        for(district d : district.values()){
            code.put(d.toString(), d.getDistrictName());
        }
        return code;
    }
}
