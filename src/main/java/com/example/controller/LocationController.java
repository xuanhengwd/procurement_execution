package com.example.controller;


import com.example.pojo.Dept;
import com.example.pojo.Location;
import com.example.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("locationController")
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/selectLocationAll")
    @ResponseBody
    List<Location> selectLocationAll(){
        return locationService.selectLocationAll();
    }

    /**
     * 添加
     * @param location
     * @return
     */
    @RequestMapping(value = "/addLocation",method = RequestMethod.POST)
    @ResponseBody
    String addLocation(Location location){
        locationService.addLocation(location);
        return "true";
    }
//
//    /**
//     * 删除
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "/addLocation",method = RequestMethod.POST)
//    @ResponseBody
//    String deleteLocationById(int id){
//        locationService.deleteLocationById(id);
//        return "true";
//    }
//
//    /**
//     * 批量删除
//     * @param ids
//     * @return
//     */
//    @RequestMapping(value = "/deleteLocationByIds",method = RequestMethod.POST)
//    @ResponseBody
//    String deleteLocationByIds(int[] ids){
//        locationService.deleteLocationByIds(ids);
//        return "true";
//    }
//
//
//    /**
//     * 修改
//     * @param location
//     * @return
//     */
//    @RequestMapping(value = "/updateLocationById",method = RequestMethod.POST)
//    @ResponseBody
//    String updateLocationById(Location location){
//        locationService.updateLocationById(location);
//        return "true";
//    }
//
//    /**
//     * 条件查询
//     * @param location
//     * @return
//     */
//    @RequestMapping(value = "/updateLocationById",method = RequestMethod.POST)
//    @ResponseBody
//    List<Location> selectLocationByCondition(Location location){
//
//        return locationService.selectLocationByCondition(location);
//    }
//
//    @RequestMapping("/locationCount")
//    @ResponseBody
//    int locationCount(){
//
//        return locationService.locationCount();
//    }
}
