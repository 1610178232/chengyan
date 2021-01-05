package com.example.psycholog.module.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.psycholog.module.entity.MhUserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MhUserDao extends BaseMapper<MhUserEntity> {

    @Select("<script>" +
            "select * from mh_user where type=#{type} and is_delete=0 " +
            "<if test=\"searchKey != null\"> and name like #{searchKey}</if> "+
            "order by id desc " +
            "limit #{pageIndex},#{pageSize} </script>")
    public List<MhUserEntity> getUserInfo( Map<String,Object> map);

    @Select("<script>" +
            "select count(*) from mh_user where type=#{type} and is_delete=0 " +
            "<if test=\"searchKey != null\"> and name like #{searchKey}</if> "+
            "order by id desc " +
            "limit #{pageIndex},#{pageSize} " +
            "</script>")
    public int count( Map<String,Object> map);
}
