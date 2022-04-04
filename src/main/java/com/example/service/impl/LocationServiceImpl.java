package com.example.service.impl;

import com.example.mapper.LocationMapper;
import com.example.pojo.Location;
import com.example.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    @Override
    public List<Location> selectLocationAll() {
        List<Location> locations = locationMapper.selectLocationAll();
        return locations;
    }

    @Override
    public void addLocation(Location location) {
        locationMapper.addLocation(location);
    }

    @Override
    public void deleteLocationById(int id) {
        locationMapper.deleteLocationById(id);
    }

    @Override
    public void deleteLocationByIds(int[] ids) {
        locationMapper.deleteLocationByIds(ids);
    }

    @Override
    public void updateLocationById(Location location) {
        locationMapper.updateLocationById(location);
    }

    @Override
    public List<Location> selectLocationByCondition(Location location) {
        String locationName = location.getLocationName();
        String deptNo = location.getDeptNo();
        if (locationName != null && locationName.length() > 0) {
            location.setLocationName("%"+locationName+"%");
        }
        if (deptNo != null && deptNo.length() > 0) {
            location.setDeptNo("%"+deptNo+"%");
        }

        List<Location> locations = locationMapper.selectLocationByCondition(location);
        return locations;
    }

    @Override
    public int locationCount() {
        return locationMapper.locationCount();
    }
}
