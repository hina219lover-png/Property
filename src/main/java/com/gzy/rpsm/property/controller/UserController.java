package com.gzy.rpsm.property.controller;

import com.gzy.rpsm.property.pojo.PageResult;
import com.gzy.rpsm.property.pojo.ResponsePojo;
import com.gzy.rpsm.property.pojo.User;
import com.gzy.rpsm.property.pojo.vo.UnpaidOwner;
import com.gzy.rpsm.property.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@RestController
@RequestMapping("/user")
@Api(tags = "业主相关api")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/insert")
    @ApiOperation(value = "新增业主信息，默认为未激活的状态，采用默认密码，生成随机账号。roomid通过级联菜单获得",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idcard", type = "String", required = true),
            @ApiImplicitParam(name = "age", type = "Integer", required = true),
            @ApiImplicitParam(name = "gender", type = "Integer", required = true),
            @ApiImplicitParam(name = "username", type = "String", required = true),
            @ApiImplicitParam(name = "user_roomid", type = "Integer", required = true)
    })

    public ResponsePojo<Integer> insertUser(@ApiIgnore User user) {
        // 验证 roomid 是否存在
        if (user.getUser_roomid() == null || user.getUser_roomid() <= 0) {
            return ResponsePojo.fail(null, "房间ID无效");
        }
        // 设置默认密码
        user.setPassword("123456");
        // 根据用户名的MD5值和时间戳生成用户账号
        String name = DigestUtils.md5DigestAsHex(user.getUsername().getBytes(StandardCharsets.UTF_8)).substring(13, 20);
        name = name.concat(String.valueOf(System.currentTimeMillis() % 10000));
        user.setAccount(name);
        // 设置用户类型和状态
        user.setUser_type((short) 3);
        user.setUser_status((short) 33);
        Integer result = null;
        try {
            result = userService.insertUser(user);
            return ResponsePojo.success(result);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return ResponsePojo.fail(result, "用户名重复");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponsePojo.fail(result, "未知错误");
        }
    }
    @GetMapping("/pageOfOwners")
    @ApiOperation(value = "分页的业主信息。默认为第一页，每页默认10条数据。",
            notes = "若当前页小于1，强制为1；若每页行数小于1，强制为1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", defaultValue = "1"),
            @ApiImplicitParam(name = "size", defaultValue = "10")
    })
    public ResponsePojo<PageResult<User>> pageOfOwners(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        PageResult<User> page = userService.pageOfOwner(current, size);
        return ResponsePojo.success(page);
    }

    @GetMapping("/updateStatus/{userid}")
    @ApiOperation(value = "修改用户状态,主要是更换业主后",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", type = "Integer", required = true,paramType = "path"),
            @ApiImplicitParam(name = "user_status", type = "Integer", required = true , paramType = "query"),
    })
    public ResponsePojo<Integer> changeUserStatus(@ApiIgnore User user) {
        int result = userService.changeUserStatus(user);
        if (result == 0){
            return ResponsePojo.fail(result,"该用户id失效");
        }
        return ResponsePojo.success(result);
    }
    @GetMapping("/id/{userid}")
    @ApiOperation( value="根据用户 id 查询用户详细信息",
            notes="<span style='color:red'>若查询失败，返回null</span>",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name="userid",type = "Integer",required = true)
    })
    public ResponsePojo<User> queryById(@PathVariable("userid") Integer id){
        User u = userService.queryById(id);
        if(Optional.ofNullable(u).isPresent()){
            return ResponsePojo.success(u) ;
        }
        return ResponsePojo.fail(u,"该 id 不存在对应用户");
    }
    @GetMapping("/unpaidOwnerList")
    @ApiOperation(value="单项费用的欠费名单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="current",defaultValue = "1"),
            @ApiImplicitParam(name="size",defaultValue = "10"),
            @ApiImplicitParam(name="typeId",required = true)
    })
    public ResponsePojo<PageResult<UnpaidOwner>> unpaidOwnerList(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            Integer typeId){
        return ResponsePojo.success(userService.pageOfUnpaidOwnerList(current,size,typeId));
    }
}
