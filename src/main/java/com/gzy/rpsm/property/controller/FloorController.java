package com.gzy.rpsm.property.controller;

import com.gzy.rpsm.property.pojo.Floor;
import com.gzy.rpsm.property.pojo.ResponsePojo;
import com.gzy.rpsm.property.service.FloorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/floor")
@Api(tags = "楼层相关 API")
public class FloorController {
    @Autowired
    FloorService floorService;

    @GetMapping("/floorsOfBuilding/{building_id}")
    @ApiOperation(value = "根据楼栋 id 查询该建设的楼层信息", responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "building_id", type = "Integer", required = true)
    })
    public ResponsePojo<List<Floor>> floorsOfBuilding(
            @PathVariable("building_id") Integer building_id) {
        List<Floor> list = floorService.floorsOfBuilding(building_id);
        if (list.size() > 0) {
            return ResponsePojo.success(list);
        }
        return ResponsePojo.fail(list, "buildingId 查询不到对应房间");
    }
}

