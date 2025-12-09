package com.gzy.rpsm.property.mapper;

import com.gzy.rpsm.property.pojo.Building;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingMapper {
    @Select("<script>select building_id,building_name from building <where> <if test='status != 0'>building_status = #{status}</if></where></script>")
    List<Building> buildingsOfStatus(@Param("status") int status);
    @Insert("insert into building value " +"(#{building_id},#{building_name},#{building_code},#{building_status}," +"#{building_floors},#{completed_date},#{design_life},#{ownership})")
    int insertBuilding(Building building);
    int insertBuildings(List<Building> buildings);
    @Update("<script>update building set building_status = #{status} where building_id in " +"<foreach open='(' close=')' collection='idList' item='i' separator=','>" +"#{i}" +"</foreach>" +"</script>")
    int changeBuildingStatus(@Param("idList") List<Integer> idList, @Param("status") Integer status);
    @Select("<script>" +
            "select * from building where building_id = #{id}" +
            "</script>")
    Building queryById(Integer id);

}

