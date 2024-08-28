package com.javaweb.controller.admin;



import com.javaweb.constant.SystemConstant;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.district;
import com.javaweb.enums.rentType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private IUserService userService;
    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute(name = "modelSearch") BuildingSearchRequest buildingSearchRequest,
                                     @RequestParam (required = false) Map<String,Object> params,
                                     @RequestParam (name = "typeCode", required = false) List<String> typeOfCode,
                                      @ModelAttribute(SystemConstant.MODEL) BuildingDTO model,
                                      HttpServletRequest request
                                      ){
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            params.put("staffId", staffId);
        }
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("modelSearch", buildingSearchRequest);

        //project-2-Tìm kiếm
        List<BuildingSearchResponse> result = buildingService.findAll(params, typeOfCode);
        modelAndView.addObject("listBuilding", result);
        modelAndView.addObject("staffs", userService.getStaffs());
        modelAndView.addObject("districtCode", district.districtCode());
        modelAndView.addObject("typeCode", rentType.typeCode());
        return modelAndView;
    }

    @GetMapping("/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute("building")BuildingDTO buildingDTO, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("building", buildingDTO);
        modelAndView.addObject("districtCode", district.districtCode());
        modelAndView.addObject("typeCodes", rentType.typeCode());
        return modelAndView;
    }

    @GetMapping("/admin/building-edit-{id}")
        public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO buildingDTO = buildingService.findBuildingById(id);
        mav.addObject("building", buildingDTO);
        mav.addObject("districtCode", district.districtCode());
         mav.addObject("typeCodes", rentType.typeCode());
         return mav;
    }
}
