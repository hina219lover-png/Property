package com.gzy.rpsm.property.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@ApiModel("响应时返回 map 结构")
public class ResponseMap<K,V> {
    @ApiModelProperty("map 结构,显示 value 的具体结构，key 通常为 string")
    private Map<K,V> map;
}
