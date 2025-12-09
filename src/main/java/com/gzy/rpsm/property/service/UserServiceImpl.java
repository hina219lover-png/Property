package com.gzy.rpsm.property.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzy.rpsm.property.mapper.UserMapper;
import com.gzy.rpsm.property.pojo.PageResult;
import com.gzy.rpsm.property.pojo.User;
import com.gzy.rpsm.property.pojo.vo.UnpaidOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int insertUser(User user) throws DuplicateKeyException, Exception {
        int result = -1;
        result = userMapper.insertUser(user);
        return result;
    }
    @Override
    public PageResult<User> pageOfOwner(int current, int size) {
        PageHelper.startPage(current, size);
        Page<User> page = userMapper.owners();
        return PageResult.restPage(page);
    }
    @Override
    public int changeUserStatus(User user) {
        return userMapper.changeUserStatus(user);
    }
    @Override
    public User queryById(int id) {return userMapper.queryById(id);}
    @Override
    public PageResult<UnpaidOwner> pageOfUnpaidOwnerList(Integer current, Integer size, Integer typeId) {
        PageHelper.startPage(current,size);
        Page<UnpaidOwner> page = userMapper.unpaidOwnerList(typeId);
        return PageResult.restPage(page);
    }
}
