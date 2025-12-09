package com.gzy.rpsm.property.service;

import com.gzy.rpsm.property.pojo.Floor;
import java.util.List;
import java.util.Map;

public interface FloorService {
    List<Floor> floorsOfBuilding(Integer buildingId);
    void insertFloors(Floor floor, Map<String,Integer> results,int size,Integer[] builtArea);
}

