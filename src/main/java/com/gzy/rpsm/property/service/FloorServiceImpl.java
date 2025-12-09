package com.gzy.rpsm.property.service;

import com.gzy.rpsm.property.mapper.FloorMapper;
import com.gzy.rpsm.property.mapper.RoomMapper;
import com.gzy.rpsm.property.pojo.Floor;
import com.gzy.rpsm.property.pojo.Room;
import com.gzy.rpsm.property.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FloorServiceImpl implements FloorService{
    @Autowired
    RoomMapper roomMapper;
    @Resource
    FloorMapper floorMapper;
    @Override
    public List<Floor> floorsOfBuilding(Integer buildingId) {
        return floorMapper.floorsOfBuilding(buildingId);
    }
    @Override
    public void insertFloors(Floor floor, Map<String,Integer> results, int size, Integer[] builtArea) {
        List<Floor> floorList = new ArrayList<Floor>(size);
        int result = -1;
        for (Floor f:floorList) {
            List<Room> list = Arrays.stream(builtArea).
                    map( i -> {
                        Room room = new Room();
                        room.setRoom_floor_id(f.getFloor_id());
                        //需拿到楼栋编号中的数字，而不是 id
                        room.setRoom_code(StringUtil.getNumberFromString(f.getBuilding_name())+"-"+f.getFloor_number()+"-");
                        room.setBuilt_up_area(i.shortValue());
                        return room;
                    }).collect(Collectors.toList());
            result = roomMapper.insertRooms(list);
            results.put("room",results.get("room") + result);
        }
        try{
            //将层数的数字序号+1，并生成列表
            for(int i = 0;i < size;i++){
                Floor tempFloor = floor.clone();
                tempFloor.setFloor_number((short)(i+1));
                floorList.add(tempFloor);
            }
            result = floorMapper.insertFloors(floorList);
            results.put("floor",results.get("floor")+result);
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}
