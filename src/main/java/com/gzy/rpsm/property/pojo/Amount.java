package com.gzy.rpsm.property.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("账单信息")
@Data
public class Amount {
    @ApiModelProperty("账单主键，唯一标识")
    private Integer amount_id;
    @ApiModelProperty("账单相关的业主")
    private Integer amount_roomid;
    @ApiModelProperty("账单类型，即费用类型的 id 主键")
    private Byte amount_type;
    @ApiModelProperty("生成账单的时间")
    private LocalDateTime amount_date;
    @ApiModelProperty(value = "需支付的费用，放大 100 倍，避免浮点数的误差",dataType = "Integer")
    private Integer amount;
    @ApiModelProperty("账单支付被支付:1、已支付，2、未支付")
    private Integer isPaid;
}
