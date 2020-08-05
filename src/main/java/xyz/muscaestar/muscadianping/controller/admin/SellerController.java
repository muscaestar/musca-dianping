package xyz.muscaestar.muscadianping.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xyz.muscaestar.muscadianping.common.*;
import xyz.muscaestar.muscadianping.model.SellerModel;
import xyz.muscaestar.muscadianping.service.SellerService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by muscaestar on 7/29/20
 *
 * @author muscaestar
 */
@RequestMapping("/admin/seller")
@Controller("/admin/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index() {
        List<SellerModel> sellerModelList = sellerService.listAll();

        ModelAndView modelAndView = new ModelAndView("/admin/seller/index");
        modelAndView.addObject("data", sellerModelList);
        modelAndView.addObject("CONTROLLER_NAME", "seller");
        return modelAndView;
    }

    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView("/admin/seller/create");
        modelAndView.addObject("CONTROLLER_NAME", "seller");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes ="application/x-www-form-urlencoded" )
    @AdminPermission
    public String create(@Valid SellerCreateReq sellerCreateReq, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrString(bindingResult));
        }
        SellerModel sellerModel = new SellerModel();
        sellerModel.setName(sellerCreateReq.getName());
        sellerService.create(sellerModel);
        return "redirect:/admin/seller/index";
    }
}
