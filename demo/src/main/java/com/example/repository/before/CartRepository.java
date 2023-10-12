package com.example.repository.before;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CartRepository {
    public int updateUpwd(@Param("uid") Integer uid, @Param("bpwd") String bpwd);
    public List<Map<String, Object>> selectCart(Integer uid);
    public int clear(Integer uid);
    public int deleteAgoods(@Param("uid") Integer uid, @Param("gid") Integer gid);
    public int putCart(@Param("uid") Integer uid,
                       @Param("gid") Integer gid,
                       @Param("bnum") Integer bnum);

    public List<Map<String, Object>> isPutCart(@Param("uid") Integer uid, @Param("gid") Integer gid);

    public int updateCart(@Param("uid") Integer uid,
                          @Param("gid") Integer gid,
                          @Param("bnum") Integer bnum);

    public  List<Map<String, Object>> isFocus(@Param("uid") Integer uid, @Param("gid") Integer gid);
    public int focus(@Param("uid") Integer uid, @Param("gid") Integer gid);
    public List<Map<String, Object>> myFocus(Integer uid);
    public List<Map<String, Object>> myOrder(Integer uid);
    public String pay(int order);
}
