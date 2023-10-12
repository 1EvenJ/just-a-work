package com.example.repository.before;

import com.example.entity.Goods;
import com.example.entity.GoodsType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexRepository {
    public List<Goods> selectAdvertisementGoods();

    public List<GoodsType> selectGoodsType();

    public List<Goods> selectRecommendGoods(Integer tid);

    public List<Goods> selectLastedGoods(Integer tid);

    public Goods selectAGoods(Integer id);

    public List<Goods> search(String mykey);
}
