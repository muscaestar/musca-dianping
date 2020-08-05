package xyz.muscaestar.muscadianping.service;

import xyz.muscaestar.muscadianping.model.SellerModel;

import java.util.List;

/**
 * Created by muscaestar on 7/29/20
 *
 * @author muscaestar
 */
public interface SellerService {
    SellerModel get(Integer id);
    SellerModel create(SellerModel sellerModel);
    List<SellerModel> listAll();

}
