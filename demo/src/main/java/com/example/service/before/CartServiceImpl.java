package com.example.service.before;

import com.example.entity.Goods;
import com.example.entity.Order;
import com.example.repository.before.CartRepository;
import com.example.repository.before.IndexRepository;
import com.example.util.MD5Util;
import com.example.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class    CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private IndexRepository indexRepository;

    @Override
    public String updateUpwd(HttpSession session, String bpwd) {
        Integer uid = MyUtil.getUser(session).getId();
        cartRepository.updateUpwd(uid, MD5Util.MD5(bpwd));
        return "forward:/user/toLogin";
    }

    @Override
    public String selectCart(Model model, HttpSession session, String act) {

        List<Map<String, Object>> list = cartRepository.selectCart(MyUtil.getUser(session).getId());
        double sum = 0;
        for (Map<String, Object> map : list) {
            sum = sum + (Double) map.get("smallsum");
        }
        model.addAttribute("total", sum);
        model.addAttribute("cartlist", list);
        //广告区商品
        model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
        //导航栏商品类型
        model.addAttribute("goodsType", indexRepository.selectGoodsType());
        if ("toCount".equals(act)) {//去结算页面
            return "user/count";
        }
        return "user/cart";
    }


    @Override
    public String clearCart(HttpSession session) {
        cartRepository.clear(MyUtil.getUser(session).getId());
        return "forward:/cart/selectCart";
    }

    @Override
    public String deleteCart(HttpSession session, Integer gid) {
        Integer uid = MyUtil.getUser(session).getId();
        cartRepository.deleteAgoods(uid, gid);
        return "forward:/cart/selectCart";
    }

    @Override
    public String putCart(Goods goods, Model model, HttpSession session) {
        Integer uid = MyUtil.getUser(session).getId();
        //如果商品已在购物车，只更新购买数量
        if (cartRepository.isPutCart(uid, goods.getId()).size() > 0) {
            cartRepository.updateCart(uid, goods.getId(), goods.getBuyNumber());
        } else {//新增到购物车
            cartRepository.putCart(uid, goods.getId(), goods.getBuyNumber());
        }
        //跳转到查询购物车
        return "forward:/cart/selectCart";
    }

    @Override
    public String focus(Model model, HttpSession session, Integer gid) {
        Integer uid = MyUtil.getUser(session).getId();
        List<Map<String, Object>> list = cartRepository.isFocus(uid, gid);
        //判断是否已收藏
        if (list.size() > 0) {
            return "no";
        } else {
            cartRepository.focus(uid, gid);
            return "ok";
        }

    }

    @Override
    public String myFocus(Model model, HttpSession session) {
        //广告区商品
        model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
        //导航栏商品类型
        model.addAttribute("goodsType", indexRepository.selectGoodsType());
        model.addAttribute("myFocus", cartRepository.myFocus(MyUtil.getUser(session).getId()));
        return "user/myFocus";
    }

    @Override
    public String myOder(Model model, HttpSession session) {
        //广告区商品
        model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
        //导航栏商品类型
        model.addAttribute("goodsType", indexRepository.selectGoodsType());
        model.addAttribute("myOrder", cartRepository.myOrder(MyUtil.getUser(session).getId()));
        return "user/myOrder";
    }

    @Override
    public String pay(Order order) {
        cartRepository.pay(order.getId());
        return "ok";
    }
}