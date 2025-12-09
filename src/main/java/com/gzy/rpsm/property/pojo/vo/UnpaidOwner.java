package com.gzy.rpsm.property.pojo.vo;

import com.gzy.rpsm.property.pojo.Amount;
import com.gzy.rpsm.property.pojo.Room;
import com.gzy.rpsm.property.pojo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UnpaidOwner {
    @ApiModelProperty("欠费人员信息")
    private User user;
    @ApiModelProperty("欠费账单信息")
    private Amount amount;
    @ApiModelProperty("欠费房间信息")
    private Room room;
}
