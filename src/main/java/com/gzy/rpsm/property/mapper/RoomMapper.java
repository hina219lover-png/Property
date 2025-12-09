package com.gzy.rpsm.property.mapper;

import com.github.pagehelper.Page;
import com.gzy.rpsm.property.pojo.Room;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoomMapper {
    @Select("select * from room where room_id = #{id}")
    Room roomById(Integer id);
    @Select("select room_id,room_code from room where room_floor_id = #{id}")
    List<Room> roomsOfFloor(Integer roomId);
    @Insert("<script>insert into room values " +
            "<foreach  collection=\"list\" index=\"i\" item=\"item\" separator=\",\">" +
            "( #{item.room_id},#{item.room_floor_id},#{item.built_up_area},4, concat(#{item.room_code},#{i}+1) ) </foreach></script>")
    @Options(useGeneratedKeys = true,keyProperty = "room_id",keyColumn = "room_id")
    int insertRooms(List<Room> room);
    @Select("<script> select " +
            "room_id,room_floor_id,built_up_area,room_status,room_code,building_name " +
            "from room,floor,building" +
            "<trim prefix='where' suffix='and room.room_floor_id = floor.floor_id " +
            "and floor.belong_building = building.building_id' prefixOverrides='and' >" +
            "<if test='floorId != null and floorId != \"\"'>" +
            "and room_floor_id = #{floorId}" +
            "</if>" +
            "<if test='builtArea != null and builtArea != \"\"'>" +
            "and built_up_area = #{builtArea}" +
            "</if>" +
            "<if test='status != null and status != \"\"'>" +
            "and room_status = #{status}" +
            "</if>" +
            "<if test='buildingNumber != null and buildingNumber != \"\"'>" +
            "and room_code like concat(#{buildingNumber},'-%') " +
            "</if>" +
            "<if test='floorNumber != null and floorNumber != \"\"'>" +
            " and room_code like concat('%',#{floorNumber},'-%') " +
            "</if>" +
            "</trim>" +
            "</script>")
    @Results(id="baseWithBuildingName"
            //,value = {@Result(column = "building_name",property = "building_name")}
    )
    Page<Room> pageOfRooms(Map<String,String> map);
    @Update("<script>update room set room_status = #{status} where room_id in " +
            "<foreach open='(' close=')' collection='idList' item='i' separator=','>" +
            "#{i}" +
            "</foreach>" +
            "</script>")
    int changeRoomStatus(@Param("idList")List<Integer> idList,@Param("status")int status);
}
