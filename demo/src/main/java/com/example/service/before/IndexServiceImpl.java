package com.example.service.before;

import com.example.repository.before.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexRepository indexRepository;

    @Override
    public String index(Model model, Integer tid) {
        if (tid == null)
            tid = 0;
        //广告区商品
        model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
        //导航栏商品类型
        model.addAttribute("goodsType", indexRepository.selectGoodsType());
        //推荐商品
        model.addAttribute("recommendGoods", indexRepository.selectRecommendGoods(tid));
        //最新商品
        model.addAttribute("lastedGoods", indexRepository.selectLastedGoods(tid));
        return "user/index";
    }

    @Override
    public String goodsDetail(Model model, Integer id) {
        //广告区商品
        model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
        //导航栏商品类型
        model.addAttribute("goodsType", indexRepository.selectGoodsType());
        //商品详情
        model.addAttribute("goods", indexRepository.selectAGoods(id));
        return "user/goodsDetail";
    }

    @Override
    public String search(Model model, String mykey) {
        //广告区商品
        model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
        //导航栏商品类型
        model.addAttribute("goodsType", indexRepository.selectGoodsType());
        //商品搜索
        model.addAttribute("searchgoods", indexRepository.search(mykey));
        return "user/searchResult";
    }
}
