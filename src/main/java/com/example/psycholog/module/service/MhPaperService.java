package com.example.psycholog.module.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.psycholog.module.dao.MhPaperDao;
import com.example.psycholog.module.entity.MhPaperEntity;
import com.example.psycholog.module.until.ParamUtils;
import com.example.psycholog.module.until.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("MhPaperService")
public class MhPaperService extends ServiceImpl<MhPaperDao, MhPaperEntity> {
        @Autowired
        private MhGradeService gradeService;

    public R paperList(Map<String,Object> map){
        ParamUtils.formatPageParam(map);
        if(map.get("searchKey")!=null && !map.get("searchKey").equals("")){
            map.put("searchKey","%"+map.get("searchKey").toString()+"%");
        }
        List<MhPaperEntity> paper = baseMapper.paperList( map);
        for(MhPaperEntity pa:paper){
            int paperStatus = gradeService.getGradeId(pa.getId(), (long) Integer.parseInt(map.get("userId").toString()));
            pa.setPaperStatus(paperStatus);
        }
        int count = baseMapper.count(map);
        return R.ok("成功").put("list",paper).put("total",count);
    }

    public R update(MhPaperEntity paper){
        if(paper.getId()!=null){
            baseMapper.updateById(paper);
        }
        return R.ok("成功");
    }

    public R delPaper(MhPaperEntity paper){
        baseMapper.deleteById(paper.getId());
        return R.ok("成功");
    }

    public R add(Map<String,Object> map){
        //添加问卷标题
        MhPaperEntity mhPaperEntity = new MhPaperEntity();
        String title = map.get("title").toString();
        mhPaperEntity.setTitle(title);
        mhPaperEntity.setCreatedtime(new Date());
        baseMapper.insert(mhPaperEntity);
        List<HashMap<String, Object>> list=(List<HashMap<String, Object>>)map.get("question");
        MhPaperEntity paper1 = new MhPaperEntity();
        if(list.size()>0){
            for(HashMap<String, Object> paperMap:list){
                //添加问题
                paper1.setPid1(mhPaperEntity.getId());
                paper1.setQuestion(paperMap.get("name").toString());
                baseMapper.insert(paper1);
                MhPaperEntity paper3 = new MhPaperEntity();
                //开始添加答案
                paper3.setPid2(paper1.getId());
                paper3.setAnswer(paperMap.get("answer1").toString());
                paper3.setGrade(Double.parseDouble(paperMap.get("grade1").toString()));
                baseMapper.insert(paper3);
                if(paperMap.get("answer2")!=null&&!paperMap.get("answer2").equals("")){
                    paper3.setAnswer(paperMap.get("answer2").toString());
                    paper3.setGrade(Double.parseDouble(paperMap.get("grade2").toString()));
                    baseMapper.insert(paper3);
                }
                if(paperMap.get("answer3")!=null&&!paperMap.get("answer3").equals("")){
                    paper3.setAnswer(paperMap.get("answer3").toString());
                    paper3.setGrade(Double.parseDouble(paperMap.get("grade3").toString()));
                    baseMapper.insert(paper3);
                }
                if(paperMap.get("answer4")!=null&&!paperMap.get("answer4").equals("")){
                    paper3.setAnswer(paperMap.get("answer4").toString());
                    paper3.setGrade(Double.parseDouble(paperMap.get("grade4").toString()));
                    baseMapper.insert(paper3);
                }
            }
        }else{
            return R.error("数据不全");
        }
        return R.ok("成功");
    }

    public R info(Map<String,Object> map){
        Map<String, Object> maps = baseMapper.info1(map);
        List<Map<String, Object>> maps1 = baseMapper.info2(Integer.parseInt(map.get("id").toString()));
        for(Map<String, Object> infoMap:maps1){
            List<Map<String, Object>> maps2 = baseMapper.info3(Integer.parseInt(infoMap.get("id").toString()));
            infoMap.put("answer",maps2);
        }
        return R.ok("成功").put("list",maps1).put("title",maps);
    }

    public R infoSt(Map<String,Object> map){
        Map<String, Object> maps = baseMapper.info1(map);
        List<Map<String, Object>> maps1 = baseMapper.info2(Integer.parseInt(map.get("id").toString()));
        for(Map<String, Object> infoMap:maps1){
            Map<String,Object> answer= baseMapper.getAnswer(Integer.parseInt(infoMap.get("id").toString()),Integer.parseInt(map.get("userId").toString()));
            infoMap.put("answerName",answer.get("answer"));
            infoMap.put("answerGrade",answer.get("grade"));
            List<Map<String, Object>> maps2 = baseMapper.info3(Integer.parseInt(infoMap.get("id").toString()));
            infoMap.put("answer",maps2);
        }
        return R.ok("成功").put("list",maps1).put("title",maps);
    }
}
