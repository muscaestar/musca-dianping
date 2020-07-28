package xyz.muscaestar.muscadianping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;
import xyz.muscaestar.muscadianping.common.BusinessException;
import xyz.muscaestar.muscadianping.common.EmBusinessError;
import xyz.muscaestar.muscadianping.dao.UserModelMapper;
import xyz.muscaestar.muscadianping.model.UserModel;
import xyz.muscaestar.muscadianping.service.UserService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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

    @Override
    @Transactional
    public UserModel register(UserModel userModel) throws BusinessException, NoSuchAlgorithmException {
        if (userModel == null) {
            return null;
        }
        userModel.setCreatedAt(new Date());
        userModel.setUpdatedAt(new Date());
        userModel.setPassword(encodeByMD5(userModel.getPassword()));

        try {
            userModelMapper.insertSelective(userModel);
        } catch (DuplicateKeyException ex) {
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
        }
        return getUserById(userModel.getId());
    }

    @Override
    public UserModel login(String telephone, String password) throws NoSuchAlgorithmException, BusinessException {
        UserModel userModel = userModelMapper.selectByTelephonePassword(telephone, encodeByMD5(password));
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.LOGIN_FAIL);
        }

        return userModel;
    }

    private String encodeByMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
    }
}
