package com.example.psycholog.module.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.psycholog.module.entity.MhMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MhMenuDao extends BaseMapper<MhMenuEntity> {

    @Select("select type from mh_user where token=#{token}")
    public String getType(String token);

    @Select("<script>" +
            "select * from mh_menu where type=#{type} and pid is null " +
            "</script>")
    public List<Map<String,Object>> menuList(Map<String, Object> map);

    @Select("<script>" +
            "select * from mh_menu where type=#{type} and pid is not null and pid=#{pid}" +
            "</script>")
    public  List<Map<String,Object>> menuList1(Map<String, Object> map);
}
