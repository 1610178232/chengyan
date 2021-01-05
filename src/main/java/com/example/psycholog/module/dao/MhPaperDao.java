package com.example.psycholog.module.dao;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.psycholog.module.entity.MhPaperEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface MhPaperDao extends BaseMapper<MhPaperEntity> {
    @Select("<script>" +
            "select id,title,date_format(createdtime,'%Y-%m-%d %H:%i:%S') createdtime from mh_paper where is_delete=0 and pid1 is null and pid2 is null  " +
            "<if test=\"searchKey != null\"> and name like #{searchKey} </if> "+
            "order by id desc " +
            "limit #{pageIndex},#{pageSize} </script>")
    public List<MhPaperEntity> paperList(Map<String,Object> map);

    @Select("<script>" +
            "select count(*) from mh_paper where is_delete=0 and pid1 is null and pid2 is null  " +
            "<if test=\"searchKey != null\"> and name like #{searchKey} </if> "+
            "</script>")
    public int count(Map<String,Object> map);

    @Select("<script> SELECT id,title,date_format(createdtime,'%Y-%m-%d %H:%i:%S') createdtime from mh_paper where id=#{id}; " +
            "</script>")
    public Map<String,Object> info1(Map<String,Object> map);
    @Select("<script> SELECT id,pid1,question from mh_paper where  pid1=#{pid1}; " +
            "</script>")
    public List<Map<String,Object>> info2(long pid1);
    @Select("<script> SELECT id,pid2,answer,grade from mh_paper where pid2=#{pid2}; " +
            "</script>")
    public List<Map<String,Object>> info3(long pid2);

    @Select("SELECT mp.grade,mp.answer from mh_paper mp LEFT JOIN mh_grade_record mgr on mp.id=mgr.answer_id where mgr.question_id=#{questionId} and mgr.user_id=#{userId}")
    public Map<String,Object> getAnswer(long questionId,long userId);
}
