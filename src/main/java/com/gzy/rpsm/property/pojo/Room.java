package com.gzy.rpsm.property.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("房屋信息，与楼层相关")
public class Room implements Cloneable{
    @ApiModelProperty("房屋的唯一标识")
    private Integer room_id;
    @ApiModelProperty("房屋所属楼层的标识id")
    private Integer room_floor_id;
    @ApiModelProperty("建筑面积")
    private Short built_up_area;
    @ApiModelProperty("房屋状态,1、自住；2、出租；3、未居住；4、未出售；5、公共空间")
    private Short room_status;
    @ApiModelProperty("房屋编号,例如 1-3-2:1号楼3层2号房")
    private String room_code;
    @ApiModelProperty("该楼层所属楼栋")
    private String building_name;
    @ApiModelProperty("该楼层所属楼层")
    private Integer floor_number;
    @Override
    public Room clone() throws CloneNotSupportedException {
        return (Room)super.clone();
    }
}
