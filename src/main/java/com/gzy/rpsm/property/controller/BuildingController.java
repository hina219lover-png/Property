package com.gzy.rpsm.property.controller;

import com.gzy.rpsm.property.pojo.*;
import com.gzy.rpsm.property.service.BuildingService;
import com.gzy.rpsm.property.service.FloorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "楼栋相关 API")
@RequestMapping("/building")
@RestController
public class BuildingController {
    @Autowired
    BuildingService buildingService;
    @Autowired
    FloorService floorService;
    @GetMapping("/buildingOfUsed")
    @ApiOperation(value = "查询在用建筑的列表",responseContainer = "List")
    public ResponsePojo<List<Building>> buildingOfUsed(){
        return ResponsePojo.success(buildingService.buildingsOfUsed());
    }
    @PostMapping("/addBuilding")
    @ApiOperation (value = "新增单个建筑")
    public ResponsePojo<Integer> addBuilding (@RequestBody Building building){
        int result = -1;
        try {
            result = buildingService.insertBuilding (building);
            return ResponsePojo.success (result);
        } catch (DuplicateKeyException e){
            return ResponsePojo.fail (result,"楼栋编号重复");
        }
    }
    @ApiOperation( value="查询楼栋分页信息",
            response = Building.class,
            responseContainer = "list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value = "当前页数",defaultValue = "1",dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value = "页面行数",dataType = "Integer",paramType = "query",defaultValue = "10")
    })
    @GetMapping("/page/building")
    public ResponsePojo<PageResult<Building>> pageOfBuilding(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size){
        PageResult<Building> result = buildingService.PageOfBuilding(current,size);
        return ResponsePojo.success(result);
    }
    @PostMapping("/addBuildings")
    @ApiOperation(value = "批量新增建筑", notes = "给出第一个楼栋的全属性，然后给出还需要多少个楼栋,每层楼房间的建筑面积")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buildingSize", value = "剩余需添加的楼栋个数", required = true),
            @ApiImplicitParam(name = "builtArea", value = "每层楼房间的建筑面积，按房间序号排列的")
    })
    public ResponsePojo<ResponseMap<String, Integer>> addBuildingsAndFloorsAndRooms(
            @RequestBody Building building,
            @RequestParam(value = "buildingSize", required = true) Integer buildingSize,
            @RequestParam(value = "builtArea", required = true) Integer[] builtArea) {
        Integer buildingResult = 0;
        List<Building> buildings = new ArrayList<>(buildingSize + 1);
        Map<String, Integer> results = new HashMap<>(); // 返回的结果集

        try {
            buildingResult = buildingService.insertBuildings(building, buildings, buildingSize);
            results.put("building", buildingResult);
        } catch (DuplicateKeyException e) {
            return ResponsePojo.fail(null, e.getMessage());
        }

        results.put("floor", 0);
        results.put("room", 0);

        for (Building b : buildings) {
            Floor floor = new Floor();
            floor.setBelong_building(b.getBuilding_id());
            floor.setRoom_number((short) builtArea.length);
            floor.setBuilding_name(b.getBuilding_name());
            floorService.insertFloors(floor, results, b.getBuilding_floors(), builtArea);
        }

        ResponseMap<String, Integer> res = new ResponseMap<>(results);
        return ResponsePojo.success(res);
    }
    @ApiOperation(value = "批量修改多个建筑的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idList",required = true,value = "需修改状态的楼栋 id 列表",dataTypeClass = List.class,paramType = "query"),
            @ApiImplicitParam(name = "status",required = true,value = "修改后的状态")
    })
    @PutMapping("/updateStatus")
    public ResponsePojo<Integer> changeBuildingStatus(
            @RequestParam("idList") List<Integer> idList,Integer status){
        if(idList.size() > 0){
            try{
                return ResponsePojo.success(buildingService.changeBuildingStatus(idList,status));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ResponsePojo.fail(null,"无数据");
    }
}
