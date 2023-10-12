package com.example.repository.admin;

import com.example.entity.Goods;
import com.example.entity.GoodsType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository {
    int selectAll();

    List<GoodsType> selectAllTypeByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);

    int deleteType(int id);

    List<Goods> selectGoods(int goodstype_id);

    int addType(GoodsType goodsType);
}
