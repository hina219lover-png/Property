package com.gzy.rpsm.property.controller;

import com.gzy.rpsm.property.pojo.PageResult;
import com.gzy.rpsm.property.pojo.ResponsePojo;
import com.gzy.rpsm.property.pojo.Room;
import com.gzy.rpsm.property.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "房间相关 API")
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @GetMapping("/roomsOfFloor/{floor_id}")
    @ApiOperation(value = "根据楼层 id 查询房间信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="floor_id",type = "Integer",required = true)
    })
    public ResponsePojo<List<Room>> roomsOfFloor(@PathVariable("floor_id")Integer id){
        List<Room> list = roomService.roomsOfFloor(id);
        if(list.size() > 0){
            return ResponsePojo.success(list);
        }
        return ResponsePojo.fail(list,"floorId 查询不到对应房间");
    }
    @ApiOperation(value = "分页查询所有房间信息，可以带关键字查询。",
            notes = "application/json 格式<br/>" +
                    "{current:当前页 default 1 required false,<br>" +
                    "size:每页数据量 default 10 required false,<br>" +
                    "builtArea:建筑面积 required false<br>" +
                    "status:房间状态 required false<br" +
                    "floorNumber:房间所属楼层数 required false<br>" +
                    "buildingNumber:房间所属建筑号 required false}")
    @PostMapping("/pageOfRoom")
    public ResponsePojo<PageResult<Room>> pageOfRooms(
            @RequestBody Map<String,String> map){
        map.putIfAbsent("current","1");
        map.putIfAbsent("size","10");
        return ResponsePojo.success(roomService.pageOfRoom(map));
    }
    @PutMapping("/updateStatus")
    @ApiOperation(value = "批量修改房间状态",notes = "页面需要勾选多个房间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idList",required = true,value = "需修改状态的房间 id 列表",dataTypeClass = List.class,paramType = "query"),
            @ApiImplicitParam(name = "status",required = true,value = "修改后的状态")
    })
    public ResponsePojo<Integer> changeRoomStatus(
            @RequestParam("idList") List<Integer> idList,Integer status){
        if(idList.size() > 0){
            try{
                return ResponsePojo.success(roomService.changeRoomStatus(idList,status));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ResponsePojo.fail(null,"无数据");
    }
}
