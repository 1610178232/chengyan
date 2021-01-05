package com.example.psycholog.module.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.psycholog.module.entity.MhRecordEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface MhRecordDao extends BaseMapper<MhRecordEntity> {

    @Select("<script>" +
            "select mr.from_id,mr.to_id,mr.content text,date_format(mr.createdtime,'%Y-%m-%d %H:%i:%S') date,mu.name,mu.img " +
            "from mh_record mr LEFT JOIN mh_user mu on mr.from_id=mu.id where mr.is_delete=0 " +
            "<if test=\"otherId != null\"> and mr.from_id = #{otherId} </if> "+
            "<if test=\"mainId != null\"> and mr.to_id = #{mainId}</if> "+
            "</script>")
    public List<Map<String,Object>> recordList(Map<String,Object> map);

    @Select("<script>" +
            "select mr.from_id,mr.to_id,mr.content text,date_format(mr.createdtime,'%Y-%m-%d %H:%i:%S') date,mu.name,mu.img " +
            "from mh_record mr LEFT JOIN mh_user mu on mr.from_id=mu.id where mr.is_delete=0 " +
            "<if test=\"otherId != null\"> and mr.from_id = #{mainId} </if> "+
            "<if test=\"mainId != null\"> and mr.to_id = #{otherId}</if> "+
            "</script>")
    public List<Map<String,Object>> mineInfo(Map<String,Object> map);

    @Select("<script>" +
            "select a.id,a.userId,a.from_id,a.to_id,a.status,a.content,date_format(a.createdtime,'%Y-%m-%d %H:%i:%S') createdtime,a.img,a.name,a.user_class,a.yard \n" +
            "from (select mr.id,mr.from_id,mr.to_id,mr.status,mr.content,date_format(mr.createdtime,'%Y-%m-%d %H:%i:%S') createdtime,mu.id userId,mu.img,mu.name,mu.user_class,mu.yard,mu.type " +
            "from mh_record mr LEFT JOIN mh_user mu on mr.to_id=mu.id where mu.type='学生' and mr.from_id=#{userId} order by mr.createdtime desc) a\n" +
            "<if test=\"searchKey != null\"> and (a.name like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or a.user_class like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or a.yard like #{searchKey}) </if> "+
            " GROUP BY a.to_id " +
            "limit #{pageIndex},#{pageSize} " +
            "</script>")
    public List<Map<String,Object>> userList1(Map<String,Object> map);

    @Select("<script>" +
            "select a.id,a.userId,a.from_id,a.to_id,a.status,a.content,date_format(a.createdtime,'%Y-%m-%d %H:%i:%S') createdtime,a.img,a.name,a.user_class,a.yard \n" +
            "from (select mr.id,mr.from_id,mr.to_id,mr.status,mr.content,date_format(mr.createdtime,'%Y-%m-%d %H:%i:%S') createdtime,mu.id userId,mu.img,mu.name,mu.user_class,mu.yard,mu.type " +
            "from mh_record mr LEFT JOIN mh_user mu on mr.from_id=mu.id where mu.type='学生' and mr.to_id=#{userId} order by mr.createdtime desc) a\n" +
            "<if test=\"searchKey != null\"> and (a.name like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or a.user_class like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or a.yard like #{searchKey}) </if> "+
            " GROUP BY a.from_id " +
            "limit #{pageIndex},#{pageSize} " +
            "</script>")
    public List<Map<String,Object>> userList2(Map<String,Object> map);

    @Select("<script>" +
            "select count(*) from (select count(*) " +
            "from mh_record mr LEFT JOIN mh_user mu on mr.from_id=mu.id where mu.type='学生'" +
            "<if test=\"searchKey != null\"> and (a.name like #{searchKey} </if>" +
            "<if test=\"searchKey != null\"> or a.user_class like #{searchKey} </if> "+
            "<if test=\"searchKey != null\"> or a.yard like #{searchKey}) </if> GROUP BY mr.from_id) a\n" +
            "</script>")
    public int count(Map<String,Object> map);

    @Select("<script>" +
            "SELECT a.id,a.from_id,a.img,a.`status`,a.to_id,a.content,date_format(a.createdtime,'%Y-%m-%d %H:%i:%S') createdtime,a.name,a.userId " +
            "from(SELECT mr.*,mu.name,mu.id userId,mu.img from mh_user mu LEFT JOIN mh_record mr on mu.id=mr.from_id where mu.type='管理员' and mr.to_id=#{userId} order by mr.createdtime desc) a " +
            "<if test=\"searchKey != null\"> and (a.name like #{searchKey} </if> "+
            "GROUP BY a.userId" +
            "</script>")
    public List<Map<String,Object>> stUserList(Map<String,Object> map);
    @Select("<script>" +
            "SELECT a.id,a.from_id,a.img,a.`status`,a.to_id,a.content,date_format(a.createdtime,'%Y-%m-%d %H:%i:%S') createdtime,a.name,a.userId " +
            "from(SELECT mr.*,mu.name,mu.id userId,mu.img from mh_user mu LEFT JOIN mh_record mr on mu.id=mr.to_id where mu.type='管理员' and mr.from_id=#{userId} order by mr.createdtime desc) a " +
            "<if test=\"searchKey != null\"> and (a.name like #{searchKey} </if> "+
            "GROUP BY a.userId" +
            "</script>")
    public List<Map<String,Object>> stUserList1(Map<String,Object> map);

    @Select("<script>" +
            "SELECT count(*) " +
            "from(SELECT count(*) from mh_user mu LEFT JOIN mh_record mr on mu.id=mr.to_id where mu.type='管理员'" +
            "<if test=\"searchKey != null\"> and (a.name like #{searchKey} </if> GROUP BY mu.id) a " +
            "</script>")
    public int countSt(Map<String,Object> map);

    @Select("SELECT id userId,name,img from mh_user where type='管理员'")
    public List<Map<String,Object>> user();

}
