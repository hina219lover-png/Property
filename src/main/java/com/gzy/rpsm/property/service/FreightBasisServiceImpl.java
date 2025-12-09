package com.gzy.rpsm.property.service;

import com.gzy.rpsm.property.mapper.FreightBasisMapper;
import com.gzy.rpsm.property.pojo.FreightBasis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreightBasisServiceImpl implements FreightBasisService{
    @Autowired
    FreightBasisMapper freightBasisMapper;
    @Override
    public int updateById(FreightBasis fh) {
        return freightBasisMapper.updateById(fh);
    }
}
