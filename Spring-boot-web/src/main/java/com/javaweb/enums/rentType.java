package com.javaweb.enums;

import java.util.*;

public enum rentType {

    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    private final String name;
    rentType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Map<String, String> typeCode(){
        Map<String, String> code = new HashMap<>();
        for(rentType it : rentType.values()){
            code.put(it.toString(), it.getName());
        }
        return code;
    }

}
