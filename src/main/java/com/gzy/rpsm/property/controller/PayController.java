package com.gzy.rpsm.property.controller;

import com.gzy.rpsm.property.pojo.PageResult;
import com.gzy.rpsm.property.pojo.ResponsePojo;
import com.gzy.rpsm.property.pojo.vo.PayInfo;
import com.gzy.rpsm.property.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/pay")
@Api(tags = "支付信息 API")
public class PayController {
    @Autowired
    PayService payService;
    @GetMapping("/payInfos")
    @ApiOperation("支付信息分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",value = "当前页数",dataTypeClass = Integer.class,defaultValue = "1"),
            @ApiImplicitParam(name="size",value = "每页大小",dataTypeClass = Integer.class,defaultValue = "10"),
            @ApiImplicitParam(name="start",value = "开始日期"),
            @ApiImplicitParam(name="end",value = "结束日期"),
            @ApiImplicitParam(name="username",value = "用户姓名"),
            @ApiImplicitParam(name="idCard",value = "身份证"),
            @ApiImplicitParam(name="floorId",value = "楼层 id"),
            @ApiImplicitParam(name="buildingId",value = "建筑 id"),
    })
    public ResponsePojo<PageResult<PayInfo>> getPaysInfo(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10")Integer size, LocalDate start, LocalDate end,
            String username, String idCard, Integer floorId,
            Integer buildingId
    ){
        if(start != null & end == null)
            end = LocalDate.now();
        PageResult<PayInfo> page = payService.pageOfPayInfo(current,size,start,end,username,idCard,floorId,buildingId);
        return ResponsePojo.success(page);
    }

}
