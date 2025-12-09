package com.gzy.rpsm.property.service;

import com.gzy.rpsm.property.pojo.PageResult;
import com.gzy.rpsm.property.pojo.Room;
import java.util.List;
import java.util.Map;

public interface RoomService {
    List<Room> roomsOfFloor(Integer floorId);
    PageResult<Room> pageOfRoom(Map<String,String> map);
    int changeRoomStatus(List<Integer> idList,int status);
}
