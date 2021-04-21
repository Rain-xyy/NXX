package com.net.nxx.service.impl;

import com.net.nxx.dao.NxxUserDao;
import com.net.nxx.model.NxxUserModel;
import com.net.nxx.service.UserService;
import com.net.nxx.vo.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: NXX
 * @description:
 * @author: Gxy-2001
 * @create: 2021-04-21
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private NxxUserDao userDao;

    /**
     * 查询一个用户的公开信息
     *
     * @param id
     * @return
     */
    @Override
    public NxxUserModel getUser(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    /**
     * 登录，安全问题未解决
     *
     * @param accountNumber
     * @param userPassword
     * @return
     */
    @Override
    public NxxUserModel userLogin(String accountNumber, String userPassword) {
        return userDao.userLogin(accountNumber, userPassword);
    }

    /**
     * 注册
     *
     * @param userModel
     * @return
     */
    @Override
    public boolean userSignIn(NxxUserModel userModel) {
        return userDao.insert(userModel) == 1;
    }

    /**
     * 修改用户公开信息，未验证用户身份
     *
     * @param userModel
     * @return
     */
    @Override
    public boolean updateUserInfo(NxxUserModel userModel) {
        return userDao.updateByPrimaryKeySelective(userModel) == 1;
    }

    /**
     * 修改密码
     *
     * @param newPassword
     * @param oldPassword
     * @param id
     * @return
     */
    @Override
    public boolean updatePassword(String newPassword, String oldPassword, Long id) {
        return userDao.updatePassword(newPassword, oldPassword, id) == 1;
    }

    @Override
    public PageVo<NxxUserModel> getUserByStatus(int status, int page, int nums) {
        List<NxxUserModel> list;
        int count = 0;
        if (status == 0) {
            count = userDao.countNormalUser();
            list = userDao.getNormalUser((page - 1) * nums, nums);
        } else {
            count = userDao.countBanUser();
            list = userDao.getBanUser((page - 1) * nums, nums);
        }
        return new PageVo<>(list, count);
    }

}
