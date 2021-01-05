package com.example.psycholog.module.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.psycholog.module.entity.MhGradeEntity;
import com.example.psycholog.module.entity.MhPaperEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MhGradeDao extends BaseMapper<MhGradeEntity> {
    @Select("<script>" +
            "SELECT mg.id,mg.grade,date_format(mg.createdtime,'%Y-%m-%d %H:%i:%S') createdtime,mu.name,mu.user_class,mp.title " +
            "from mh_grade mg LEFT JOIN mh_user mu on mg.user_id=mu.id LEFT JOIN mh_paper mp on mg.paper_id=mp.id where mg.is_delete=0 " +
            "<if test=\"searchKey != null\"> and (mp.title like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or mu.user_class like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or mu.name like #{searchKey}) </if> "+
            "order by mg.createdtime desc " +
            "limit #{pageIndex},#{pageSize} </script>")
    public List<Map<String,Object>> gradeList(Map<String,Object> map);
    @Select("<script>" +
            "SELECT count(*) " +
            "from mh_grade mg LEFT JOIN mh_user mu on mg.user_id=mu.id LEFT JOIN mh_paper mp on mg.paper_id=mp.id where mg.is_delete=0  " +
            "<if test=\"searchKey != null\"> and (mp.title like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or mu.user_class like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or mu.name like #{searchKey}) </if> "+
            "order by mg.createdtime desc " +
            "limit #{pageIndex},#{pageSize} </script>")
    public int count(Map<String,Object> map);

    @Select("<script>" +
            "SELECT mg.id,mg.grade,date_format(mg.createdtime,'%Y-%m-%d %H:%i:%S') createdtime,mu.name,mu.user_class,mp.title " +
            "from mh_grade mg LEFT JOIN mh_user mu on mg.user_id=mu.id LEFT JOIN mh_paper mp on mg.paper_id=mp.id where mg.is_delete=0 and mu.id=#{userId} " +
            "<if test=\"searchKey != null\"> and (mp.title like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or mu.user_class like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or mu.name like #{searchKey}) </if> "+
            "order by mg.createdtime desc " +
            "limit #{pageIndex},#{pageSize} </script>")
    public List<Map<String,Object>> gradeStList(Map<String,Object> map);
    @Select("<script>" +
            "SELECT count(*) " +
            "from mh_grade mg LEFT JOIN mh_user mu on mg.user_id=mu.id LEFT JOIN mh_paper mp on mg.paper_id=mp.id where mg.is_delete=0 and mu.id=#{userId}  " +
            "<if test=\"searchKey != null\"> and (mp.title like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or mu.user_class like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or mu.name like #{searchKey}) </if> "+
            "order by mg.createdtime desc " +
            "limit #{pageIndex},#{pageSize} </script>")
    public int countSt(Map<String,Object> map);


}
