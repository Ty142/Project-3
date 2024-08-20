
package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
public class BuildingCustomRepositoryImpl implements BuildingCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
        querySQLJoin(buildingSearchRequest, sql);
        sql.append("WHERE 1 = 1 ");
        queryNormal(sql, buildingSearchRequest);
        querySpecial(sql, buildingSearchRequest);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    private void querySQLJoin(BuildingSearchRequest buildingSearchRequest, StringBuilder sql) {

        Long staffId = buildingSearchRequest.getStaffId();
        if(staffId != null) {
            sql.append(" JOIN assignmentbuilding ass ON b.id = ass.buildingid ");
            sql.append(" JOIN user u ON ass.staffid = u.id ");
        }
    }
    public static void queryNormal(StringBuilder sql, BuildingSearchRequest buildingSearchRequest){
        try{
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for(Field item : fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId") && !fieldName.equals("typeCode")
                        && !fieldName.startsWith("rentPrice") && !fieldName.startsWith("area")) {
                    Object value = item.get(buildingSearchRequest);
                    if(value != null && !value.toString().isEmpty()) {
                        if (item.getType().getName().equals("java.lang.Long")) {
                            sql.append("AND b." + fieldName + " = " + value + " ");
                        } else {
                            sql.append("AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void querySpecial(StringBuilder sql, BuildingSearchRequest buildingSearchRequest){
        Long staffId = buildingSearchRequest.getStaffId();
        if(staffId != null){
            sql.append("AND u.id" + " = " + staffId + " ");
        }

        Long areaTo = buildingSearchRequest.getAreaTo();
        Long areaFrom = buildingSearchRequest.getAreaFrom();
        if(areaFrom != null || areaTo != null){
            sql.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id = r.buildingid ");
            if(areaFrom != null){
                sql.append("AND r.value >= " + areaFrom + " ");
            }
            if(areaTo != null){
                sql.append("AND r.value <= " + areaTo + " ");
            }
            sql.append(") ");
        }

        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();
        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        if(rentPriceFrom != null || rentPriceTo != null){
            if(rentPriceFrom != null){
                sql.append("AND b.rentprice >= " + rentPriceFrom + " ");
            }
            if(rentPriceTo != null){
                sql.append("AND b.rentprice <= " + rentPriceTo + " ");
            }
        }
        List<String> typeCode = buildingSearchRequest.getTypeCode();
        if(typeCode != null && typeCode.size() > 0){
            sql.append(" AND (");
            String tmp = typeCode.stream().map(it-> "b.type LIKE " + "'%" + it + "%'").collect(Collectors.joining(" OR "));
            sql.append(tmp + " ) ");
        }
    }
}

//
//    public void querySQLJoin(BuildingSearchRequest buildingSearchRequest, StringBuilder join) {
//        Long StaffId = buildingSearchRequest.getStaffId();
//        if (StaffId != null) {
//            join.append(" INNER JOIN assignmentbuilding ass ON ass.staffid = b.id");
//            join.append(" JOIN user u ON ass.staffid = u.id ");
//        }
//
//    }
//
//    public void querySQLnomal(BuildingSearchRequest buildingSearchRequest, StringBuilder where) {
//        try {
//            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
//            for (Field item : fields) {
//                item.setAccessible(true);
//                String fieldName = item.getName();
//                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("rentPrice") && !fieldName.startsWith("area") ) {
//                    Object value = item.get(buildingSearchRequest);
//                    if (value != null && value != "") {
//                        if (item.getType().getName().equals("java.lang.Long")) {
//                            where.append(" AND b." + fieldName.toLowerCase() + " = " + value);
//                        } else  {
//                            where.append(" AND b." + fieldName.toLowerCase() + " like '%" + value + "%'");
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void querySQLSpecial(BuildingSearchRequest buildingSearchRequest, StringBuilder where) {
//        Long areaTo = buildingSearchRequest.getAreaTo();
//        Long areaFrom = buildingSearchRequest.getAreaFrom();
//        if (areaFrom != null || areaTo != null) {
//            if (areaFrom != null) {
//                where.append(" AND ra.value >= " + areaFrom);
//            }
//            if (areaTo != null) {
//                where.append(" AND ra.value <= " + areaTo);
//            }
//        }
//        Long staffId = buildingSearchRequest.getStaffId();
//        if (staffId != null) {
//            where.append(" AND sb.staffId = " + staffId);
//        }
//
//        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();
//        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
//        if (rentPriceFrom != null) {
//            where.append(" AND b.rentprice >= " + rentPriceFrom);
//        }
//        if (rentPriceTo != null) {
//            where.append(" AND b.rentprice <= " + rentPriceTo);
//        }
//
//        List<String> typeCode = buildingSearchRequest.getTypeCode();
//        if (typeCode != null && typeCode.size() > 0) {
//            where.append(" AND (");
//            String tmp = typeCode.stream().map(it-> "b.type LIKE " + "'%" + it + "%'").collect(Collectors.joining(" OR "));
//            where.append(tmp + " )");
//        }
//    }
//
//    @Override
//    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest) {
//        StringBuilder sql = new StringBuilder("SELECT DISTINCT b.* FROM building b ");
//        querySQLJoin(buildingSearchRequest, sql);
//        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
//        sql.append(where);
//        querySQLnomal(buildingSearchRequest, where);
//        querySQLSpecial(buildingSearchRequest, where);
//        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
//        return query.getResultList();
//    }
//}

