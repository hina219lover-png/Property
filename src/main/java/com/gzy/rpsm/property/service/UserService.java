package com.gzy.rpsm.property.service;

import com.gzy.rpsm.property.pojo.PageResult;
import com.gzy.rpsm.property.pojo.User;
import com.gzy.rpsm.property.pojo.vo.UnpaidOwner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    int insertUser(User user)throws DuplicateKeyException,Exception;
    // 在 UserService 接口中添加
    PageResult<User> pageOfOwner(int current, int size);
    int changeUserStatus(User user);
    User queryById(int id);
    PageResult<UnpaidOwner> pageOfUnpaidOwnerList(Integer current, Integer size, Integer typeId);
}
