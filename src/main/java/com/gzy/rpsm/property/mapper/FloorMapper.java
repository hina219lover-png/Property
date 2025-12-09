package com.gzy.rpsm.property.mapper;

import com.gzy.rpsm.property.pojo.Floor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorMapper {
    @Select("select floor_id,floor_number from floor where belong_building = #{id}")
    List<Floor> floorsOfBuilding(Integer id);
    @Insert("<script>insert into floor values " +
            "<foreach  collection=\"list\" index=\"i\" item=\"item\" separator=\",\">" +
            "( #{item.floor_id},#{item.belong_building},#{item.floor_number},#{item.room_number} ) </foreach></script>")
    @Options(useGeneratedKeys = true,keyProperty = "floor_id",keyColumn = "floor_id")
    int insertFloors(List<Floor> floors);
}
