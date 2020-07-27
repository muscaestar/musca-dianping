package xyz.muscaestar.muscadianping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.muscaestar.muscadianping.dao.UserModelMapper;
import xyz.muscaestar.muscadianping.model.UserModel;
import xyz.muscaestar.muscadianping.service.UserService;

/**
 * Created by muscaestar on 7/27/20
 *
 * @author muscaestar
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserModelMapper userModelMapper;

    @Override
    public UserModel getUserById(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }
}
