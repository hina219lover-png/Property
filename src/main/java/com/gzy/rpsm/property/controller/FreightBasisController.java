package com.gzy.rpsm.property.controller;

import com.gzy.rpsm.property.Data.FreightBasisData;
import com.gzy.rpsm.property.pojo.FreightBasis;
import com.gzy.rpsm.property.pojo.ResponsePojo;
import com.gzy.rpsm.property.service.FreightBasisService;
import com.gzy.rpsm.property.utils.StringUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/freightBasis")
@Api(tags = "费率相关 API")
public class FreightBasisController {

    @Autowired
    private FreightBasisData freightBasisData;

    @Autowired
    private FreightBasisService freightBasisService;

    @ApiOperation(value = "查询费率信息")
    @ApiParam
    @PostMapping("/queryAll")
    public ResponsePojo<FreightBasisData> queryAll(){
        return ResponsePojo.success(freightBasisData);
    }

    @ApiOperation(value = "修改费用说明，可以修改单价、说明或名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "basisPrice",value = "单价，两位小数"),
            @ApiImplicitParam(name = "intro",value = "费用介绍"),
            @ApiImplicitParam(name = "basis_name",value = "费用名称"),
            @ApiImplicitParam(name = "freight_basis_id",value = "费用 id",dataType = "int")
    })
    @PutMapping("/updateById")
    public ResponsePojo<Integer> update(String basisPrice, @ApiIgnore FreightBasis fh, HttpServletResponse res){
        try{
            fh.setPrice(StringUtil.stringPriceToInt(basisPrice));
        }catch (Exception e){
            try {
                res.sendError(403,e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }
        int result = freightBasisService.updateById(fh);
        switch (result){
            case 1:
                freightBasisData.flushData();
                return ResponsePojo.success(result);
            case 0:
                return ResponsePojo.fail(0,"该 id 没有找到对应信息");
            default:
                return ResponsePojo.fail(2,"参数有误，修改失败");
        }
    }
}
