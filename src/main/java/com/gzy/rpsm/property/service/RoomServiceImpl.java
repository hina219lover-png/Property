package com.gzy.rpsm.property.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzy.rpsm.property.mapper.RoomMapper;
import com.gzy.rpsm.property.pojo.PageResult;
import com.gzy.rpsm.property.pojo.Room;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService{
    @Resource
    RoomMapper roomMapper;
    @Override
    public List<Room> roomsOfFloor(Integer floorId) {
        return roomMapper.roomsOfFloor(floorId);
    }
    @Override
    public PageResult<Room> pageOfRoom(Map<String, String> map) {
        PageHelper.startPage(Integer.parseInt(map.get("current")),Integer.valueOf(map.get("size")));
        Page<Room> rooms = roomMapper.pageOfRooms(map);
        return PageResult.restPage(rooms);
    }
    @Override
    public int changeRoomStatus(List<Integer> idList, int status) {
        return roomMapper.changeRoomStatus(idList,status);
    }
}
