package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.common.lang.vo.UserInfo;
import com.blog.entity.User;

import java.util.List;

/**
 * 服务类
 *
 * @author hanxiaofei
 * @date  2021/4/5
 */
public interface UserService extends IService<User> {
    /**
     * 查询所有用户（只含有部分信息）
     *
     * @return 用户（只含有部分信息）list
     */
    List<UserInfo> getUserInfoList();

}
