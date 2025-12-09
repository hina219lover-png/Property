package com.gzy.rpsm.property.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("楼层信息。包括每层楼多少房间，楼层序号")
public class Floor implements Cloneable{
    @ApiModelProperty("楼层信息标识")
    private Integer floor_id;
    @ApiModelProperty("楼层所属建筑标识")
    private Integer belong_building;
    @ApiModelProperty("第几层")
    private Short floor_number;
    @ApiModelProperty("该楼层房间数量")
    private Short room_number;
    @ApiModelProperty("该楼层所属楼栋")
    private String building_name;
    @Override
    public Floor clone() throws CloneNotSupportedException {
        return (Floor)super.clone();
    }
}
