package com.gzy.rpsm.property.mapper;

import com.github.pagehelper.Page;
import com.gzy.rpsm.property.pojo.Pay;
import com.gzy.rpsm.property.pojo.vo.PayInfo;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;

public interface PayMapper {
    @Select("<script>" +
            "select payInfo.pay_id, payInfo.building_id, payInfo.room_id, u.userid " +
            "from (select p.pay_id, b.building_id, r.room_id, f.floor_id " +
            "      from (select pay_id, pay_room_id from pay " +
            "            <where> <if test='start != null and end != null'>and pay_date BETWEEN #{start} and #{end}</if> </where>) p " +
            "      join building b on f.belong_building = b.building_id " +
            "      join (select room_id, room_floor_id from room where room_floor_id = #{floorId} " +
            "            union select room_id, room_floor_id from room r join floor f on r.room_floor_id = f.floor_id " +
            "                   where f.belong_building = #{buildingId}) r on p.pay_room_id = r.room_id " +
            "      join floor f on r.room_floor_id = f.floor_id) payInfo " +
            "left join user u on u.user_roomid = payInfo.room_id " +
            "</script>")
    @Results(id = "payInfo", value = {
            @Result(property = "user", column = "userid", one = @One(select = "com.gzy.rpsm.property.mapper.UserMapper.queryById")),
            @Result(property = "building", column = "building_id", one = @One(select = "com.gzy.rpsm.property.mapper.BuildingMapper.queryById")),
            @Result(property = "room", column = "room_id", one = @One(select = "com.gzy.rpsm.property.mapper.RoomMapper.roomById")),
            @Result(property = "pay", column = "pay_id", one = @One(select = "com.gzy.rpsm.property.mapper.PayMapper.queryById"))
    })
    Page<PayInfo> selectPayInfoOnCondition(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("username") String username,
            @Param("idCard") String idCard,
            @Param("floorId") Integer floorId,
            @Param("buildingId") Integer buildingId
    );
    @Select("<script>" +
            "select * from pay where pay_id = #{id}" +
            "</script>")
    Pay queryById(Integer id);

}
