package com.gzy.rpsm.property.Data;

import com.gzy.rpsm.property.mapper.FreightBasisMapper;
import com.gzy.rpsm.property.pojo.FreightBasis;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;  // 修改导入包
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Scope("singleton")  // 现在使用正确的作用域注解
@ApiModel
public class FreightBasisData {
    @ApiModelProperty(hidden = true) // 该信息不需要显示
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private FreightBasisMapper freightBasisMapper;
    @ApiModelProperty("水费信息")
    private FreightBasis water;
    @ApiModelProperty("电费信息")
    private FreightBasis electricity;
    @ApiModelProperty("物业费信息")
    private FreightBasis property;

    public FreightBasisData(FreightBasisMapper freightBasisMapper)
    {
        this.freightBasisMapper = freightBasisMapper;
        flushData();
    }

    public void flushData(){
        List<FreightBasis> list = freightBasisMapper.findAll();
        synchronized (this){
            this.water = list.get(0);
            this.electricity = list.get(1);
            this.property = list.get(2);
        }
    }
}
