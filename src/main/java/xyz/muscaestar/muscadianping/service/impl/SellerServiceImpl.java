package xyz.muscaestar.muscadianping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.muscaestar.muscadianping.dao.SellerModelMapper;
import xyz.muscaestar.muscadianping.model.SellerModel;
import xyz.muscaestar.muscadianping.service.SellerService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by muscaestar on 7/29/20
 *
 * @author muscaestar
 */
@Service
public class SellerServiceImpl implements SellerService {


    @Autowired
    SellerModelMapper sellerModelMapper;

    @Override
    public SellerModel get(Integer id) {
        return sellerModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public SellerModel create(SellerModel sellerModel) {
        sellerModel.setCreatedAt(new Date());
        sellerModel.setUpdatedAt(new Date());
        sellerModel.setDisabledFlag(0);
        sellerModel.setRemarkScore(BigDecimal.ZERO);
        sellerModelMapper.insertSelective(sellerModel);
        return get(sellerModel.getId());
    }

    @Override
    public List<SellerModel> listAll() {
        return sellerModelMapper.listAll();
    }
}
