package com.gzy.rpsm.property.service;

import com.gzy.rpsm.property.pojo.PageResult;
import com.gzy.rpsm.property.pojo.vo.PayInfo;

import java.time.LocalDate;

public interface PayService {
    PageResult<PayInfo> pageOfPayInfo(Integer current, Integer size, LocalDate start,
                                      LocalDate end, String username, String idCard,
                                      Integer floorId, Integer buildingId);
}
