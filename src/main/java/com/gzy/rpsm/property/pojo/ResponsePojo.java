package com.gzy.rpsm.property.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class ResponsePojo<T> {
    private T data;
    private String message;
    private int code;//成功为1，失败为2

    private ResponsePojo(T t) {
        this.data = t;
        this.code = 1;
        this.message = "success";
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private ResponsePojo(T t, String message, int code) {
        this.data = t;
        this.message = message;
        this.code = code;
    }

    public static <T> ResponsePojo<T> success(T t) {
        return new ResponsePojo<>(t);
    }
    public static <T> ResponsePojo<T> fail(T t, String message) {
        return new ResponsePojo<>(t, message, 2);
    }
    @Data
    @ApiModel
    public class PageResult<T> {
        @ApiModelProperty(value="响应的数据")
        private T data;
        @ApiModelProperty("响应的说明信息")
        private String message;
        @ApiModelProperty("响应状态码，1：成功，2：失败")
        private int code;//成功为1，失败为2
    }
}


