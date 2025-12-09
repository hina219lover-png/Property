package com.gzy.rpsm.property.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
@ApiModel("建筑信息")
public class Building implements Cloneable{
    @ApiModelProperty("建筑信息的标识")
    private Integer building_id;
    @ApiModelProperty("建筑编号")
    private String building_code;
    @ApiModelProperty("建筑名称")
    private String building_name;
    @ApiModelProperty("建筑状态")
    private Short building_status;
    @ApiModelProperty("建筑楼层数")
    private Short building_floors;
    @ApiModelProperty("建成时间")
    private LocalDate completed_date;
    @ApiModelProperty("设计寿命")
    private Short design_life;
    @ApiModelProperty("产权年限")
    private Short ownership;
    @Override
    public Building clone() throws CloneNotSupportedException{
        return (Building)super.clone();
    }
}
