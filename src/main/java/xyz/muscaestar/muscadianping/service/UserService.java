package xyz.muscaestar.muscadianping.service;

import xyz.muscaestar.muscadianping.common.BusinessException;
import xyz.muscaestar.muscadianping.model.UserModel;

import java.security.NoSuchAlgorithmException;

/**
 * Created by muscaestar on 7/27/20
 *
 * @author muscaestar
 */
public interface UserService {
    UserModel getUserById(Integer id);

    UserModel register(UserModel userModel) throws BusinessException, NoSuchAlgorithmException;
}
