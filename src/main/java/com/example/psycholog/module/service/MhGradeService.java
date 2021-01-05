package com.example.psycholog.module.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.psycholog.module.dao.MhGradeDao;
import com.example.psycholog.module.dao.MhGradeRecordDao;
import com.example.psycholog.module.dao.MhPaperDao;
import com.example.psycholog.module.entity.MhGradeEntity;
import com.example.psycholog.module.entity.MhGradeRecordEntity;
import com.example.psycholog.module.entity.MhPaperEntity;
import com.example.psycholog.module.until.ParamUtils;
import com.example.psycholog.module.until.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("MhGradeService")
public class MhGradeService extends ServiceImpl<MhGradeDao, MhGradeEntity> {
    @Autowired
    private MhPaperDao paperDao;
    @Autowired
    private MhGradeRecordDao recordDao;
    public R gradeList(Map<String,Object> map){
        ParamUtils.formatPageParam(map);
        if(map.get("searchKey")!=null && !map.get("searchKey").equals("")){
            map.put("searchKey","%"+map.get("searchKey").toString()+"%");
        }
        List<Map<String,Object>> paper = baseMapper.gradeList(map);
        int count = baseMapper.count(map);
        return R.ok("成功").put("list",paper).put("total",count);
    }

    public R add(Map<String,Object> map){
        long paperId=(long)Integer.parseInt(map.get("id").toString());
        long userId=(long)Integer.parseInt(map.get("userId").toString());
        double grade=0.0;
        List<HashMap<String, Object>> list=(List<HashMap<String, Object>>)map.get("question");
        for(HashMap<String, Object> paperMap:list){
            long answerId=(long)Integer.parseInt(paperMap.get("type").toString());
            //添加问卷完成记录
            MhGradeRecordEntity mhGradeRecordEntity = new MhGradeRecordEntity();
            mhGradeRecordEntity.setPaperId(paperId);
            mhGradeRecordEntity.setAnswerId(answerId);
            mhGradeRecordEntity.setUserId(userId);
            mhGradeRecordEntity.setQuestionId((long)Integer.parseInt(paperMap.get("id").toString()));
            recordDao.insert(mhGradeRecordEntity);
            //添加问卷分数
            MhPaperEntity paper1 = new MhPaperEntity();
            paper1.setId(answerId);
            MhPaperEntity paper2 = paperDao.selectById(paper1.getId());
             grade = grade+paper2.getGrade();

        }
        MhGradeEntity grade1 = new MhGradeEntity();
        grade1.setPaperId(paperId);
        grade1.setUserId(userId);
        grade1.setGrade(grade);
        grade1.setCreatedtime(new Date());
        baseMapper.insert(grade1);
        return R.ok("成功");
    }

    public int getGradeId( long paperId,long userId){
        MhGradeEntity mhGradeEntity = new MhGradeEntity();
        mhGradeEntity.setPaperId(paperId);
        mhGradeEntity.setUserId(userId);
        MhGradeEntity mhGradeEntity1 = baseMapper.selectOne(mhGradeEntity);
        int paperStatus;
        if(mhGradeEntity1!=null&&!mhGradeEntity1.equals("")){
            paperStatus=1;
        }else {
            paperStatus=0;
        }
        return paperStatus;
    }

    public R gradeStList(Map<String,Object> map){
        ParamUtils.formatPageParam(map);
        if(map.get("searchKey")!=null && !map.get("searchKey").equals("")){
            map.put("searchKey","%"+map.get("searchKey").toString()+"%");
        }
        List<Map<String,Object>> paper = baseMapper.gradeStList(map);
        int count = baseMapper.countSt(map);
        return R.ok("成功").put("list",paper).put("total",count);
    }
}
