package com.gzy.rpsm.property.pojo.vo;

import com.gzy.rpsm.property.pojo.Building;
import com.gzy.rpsm.property.pojo.Pay;
import com.gzy.rpsm.property.pojo.Room;
import com.gzy.rpsm.property.pojo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("支付信息")
public class PayInfo {
    @ApiModelProperty("支付人员信息")
    private User user;
    @ApiModelProperty("支付人员所住建筑")
    private Building building;
    @ApiModelProperty("支付人员所住房间")
    private Room room;
    @ApiModelProperty("支付金额相关")
    private Pay pay;
}
