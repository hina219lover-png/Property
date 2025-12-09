package com.gzy.rpsm.property.service;

import com.gzy.rpsm.property.pojo.Building;
import com.gzy.rpsm.property.pojo.PageResult;

import java.util.List;

public interface BuildingService {
    List<Building> buildingsOfUsed();
    int insertBuilding(Building building);
    PageResult<Building> PageOfBuilding(int current, int size);
    int insertBuildings(Building building,List<Building> buildings,int size);
    int changeBuildingStatus(List<Integer> idList,Integer status);
}
