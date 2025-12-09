package com.gzy.rpsm.property.mapper;

import com.github.pagehelper.Page;
import com.gzy.rpsm.property.pojo.User;
import com.gzy.rpsm.property.pojo.vo.UnpaidOwner;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user value (NULL,#{account},#{password}," +
            "#{idcard},#{username},#{gender}," +
            "#{age},#{user_type},#{user_status},#{user_roomid})")
    int insertUser(User user);
    Page<User> owners();
    @Update("update user set user_status = #{user_status} where userid = #{userid}")
    int changeUserStatus(User user);
    @Select("select userid,username,account,idcard,gender,age,user_type,user_status,user_roomid from user where userid = #{id}")
    User queryById (int id);
    @Select("select a.amount_date,a.amount,`user`.userid,`user`.username,room.room_code from (select * from amount" +
            "    where amount.isPaid = 2 and amount.amount_type = #{typeId}) as a" +
            "    LEFT JOIN room on a.amount_roomid = room.room_id" +
            "    left join user on room.room_id = `user`.user_roomid" +
            "    order by a.amount_date asc ,`user`.username")
    /* 引用已经定义的resultmap */
    @ResultMap("com.gzy.rpsm.property.mapper.UserMapper.unpaidOwner")
    Page<UnpaidOwner> unpaidOwnerList(Integer typeId);
}
