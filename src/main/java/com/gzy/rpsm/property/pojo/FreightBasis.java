package com.gzy.rpsm.property.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("费用信息,包括单价,计费方式")
@Data
public class FreightBasis {
    @ApiModelProperty("费用信息的标识")
    private Integer freight_basis_id;
    @ApiModelProperty("费用单价")
    private Integer price;
    @ApiModelProperty("费用介绍")
    private String intro;
    @ApiModelProperty("费用名称")
    private String basis_name;
}
