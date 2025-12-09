package com.gzy.rpsm.property.pojo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
public final class PageResult<T> {
    private final Integer pageNum;
    private final Integer pageSize;
    private final Long total;
    private final List<T> list;
    private PageResult(@NonNull Integer pageNum,
                       @NonNull Integer pageSize,
                       @NonNull Long total,
                       @NonNull List<T> list){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }
    public static <T> PageResult<T> restPage(@NonNull Integer pageNum,
                                             @NonNull Integer pageSize,
                                             @NonNull Long total,
                                             @NonNull List<T> list){
        return new PageResult<>(pageNum, pageSize, total, Optional.ofNullable(list).orElse(Collections.emptyList()));
    }
    /**
    *将mybatis分页结果转化为通用结果
    */
    public static <T> PageResult<T> restPage(@NonNull Page<T> page){
        return new PageResult<>(page.getPageNum(), page.getPageSize(), page.getTotal(), page.getResult());
    }
    /**
     *将mybatis分页结果转化为通用结果
     */
    public static <T> PageResult<T> restPage(@NonNull PageInfo<T> page){
        return new PageResult<>(page.getPageNum(), page.getPageSize(), page.getTotal(), page.getList());
    }
}
