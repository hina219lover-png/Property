package com.gzy.rpsm.property.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Data
@ApiModel(value ="用户信息，包含管理员，业务人员和住户")
public class User {
    @ApiModelProperty(value = "用户账号")
    private String account;
    @ApiModelProperty(value = "用户姓名")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "用户类型")
    private Short user_type;
    @ApiModelProperty(value = "用户id")
    private Integer userid;
    @ApiModelProperty(value = "用户姓别")
    private Short gender;
    @ApiModelProperty(value = "用户年龄")
    private Short age;
    @ApiModelProperty(value = "用户状态")
    private Short user_status;
    @ApiModelProperty(value = "用户所属房屋id")
    private Short user_roomid;
    @ApiModelProperty(value = "身份证号")
    private String idcard;
    @ApiModelProperty(value = "用户房间信息，可能一个人有多套房")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Room> rooms;

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_type=" + user_type +
                ", userid=" + userid +
                ", gender=" + gender +
                ", age=" + age +
                ", user_status=" + user_status +
                ", user_roomid=" + user_roomid +
                ", idcard='" + idcard + '\'' +
                ", rooms=" + rooms +
                '}';
    }
    public void setPassword(String password) {
        if (password.length()!=32){
            this.password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        }else{
            this.password = password;
        }
    }
}
