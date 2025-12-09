package com.gzy.rpsm.property.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@ApiModel("支付信息")
@Data
public class Pay {
    @ApiModelProperty("支付信息的唯一标识")
    private Integer pay_id;
    @ApiModelProperty("支付费用的业主 id")
    private Integer pay_room_id;
    @ApiModelProperty("支付的费用")
    private Double pay;
    @ApiModelProperty("支付的时间")
    private LocalDateTime pay_date;
    @ApiModelProperty("支付的费用类型，即费用主键")
    private Integer pay_type;
}
