package com.gzy.rpsm.property.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzy.rpsm.property.mapper.PayMapper;
import com.gzy.rpsm.property.pojo.PageResult;
import com.gzy.rpsm.property.pojo.vo.PayInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PayServiceImpl implements PayService{
    @Autowired
    PayMapper payMapper;
    @Override
    public PageResult<PayInfo> pageOfPayInfo(Integer current,
                                             Integer size, LocalDate start, LocalDate end, String username,
                                             String idCard, Integer floorId, Integer buildingId) {
        PageHelper.startPage(current,size);
        Page<PayInfo> page = payMapper.selectPayInfoOnCondition(start,end,username,idCard,floorId,buildingId);
        return PageResult.restPage(page);
    }
}
