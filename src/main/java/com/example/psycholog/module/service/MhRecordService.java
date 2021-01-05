package com.example.psycholog.module.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.psycholog.module.dao.MhRecordDao;
import com.example.psycholog.module.entity.MhRecordEntity;
import com.example.psycholog.module.until.ParamUtils;
import com.example.psycholog.module.until.R;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Comparator;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("MhRecordService")
public class MhRecordService extends ServiceImpl<MhRecordDao, MhRecordEntity> {

    //聊天记录（管理员）
    public R getRecordList(Map<String,Object> map){
        List<Map<String, Object>> maps = baseMapper.recordList(map);
        for (Map<String, Object> info1 : maps){
            info1.put("mine",false);
            Map map1 = new HashMap();
            map1.put("text",info1.get("text"));
            JSONObject json =new JSONObject(map1);
            info1.put("text",json);
        }
        List<Map<String, Object>> maps1 = baseMapper.mineInfo(map);
        for (Map<String, Object> info2 : maps1){
            info2.put("mine",true);
            Map map1 = new HashMap();
            map1.put("text",info2.get("text"));
            JSONObject json =new JSONObject(map1);
            info2.put("text",json);
            maps.add(info2);
        }
        //按时间排序
        Collections.sort(maps, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String s1 = o1.get("date").toString();
                String s2 = o2.get("date").toString();
                long val1 = 0;
                long val2 = 0;
                try {
                    val1 = format.parse(s1).getTime();
                    val2 = format.parse(s2).getTime();
                } catch (ParseException e) {
                }
                return (int) (val1 - val2);
            }
        });
        return R.ok("成功").put("list",maps);
    }


    //保存聊天信息（管理员）
    public R addRecord(MhRecordEntity recordEntity){
        MhRecordEntity mhRecordEntity = new MhRecordEntity();
        mhRecordEntity.setId(recordEntity.getId());
        recordEntity.setCreatedtime(new Date());
        Integer insert = baseMapper.insert(recordEntity);
        mhRecordEntity.setStatus(1);
        baseMapper.updateById(mhRecordEntity);
        if(insert>0){
            return R.ok("成功");
        }else{
            return R.error("失败");
        }
    }

    //聊天列表（管理员）
    public R getChawUser(Map<String,Object> map){
        ParamUtils.formatPageParam(map);
        if(map.get("searchKey")!=null && !map.get("searchKey").equals("")){
            map.put("searchKey","%"+map.get("searchKey").toString()+"%");
        }
        List<Map<String, Object>> maps2 = new ArrayList<>();
        List<Map<String, Object>> maps = baseMapper.userList1(map);
        List<Map<String, Object>> maps1 = baseMapper.userList2(map);
        if(maps.size()==0){
            maps2=maps1;
        }
        if(maps1.size()==0){
            maps2=maps;
        }
        if(maps.size()>maps1.size()){
            for(Map<String, Object> m1:maps){
                for(Map<String, Object> m2:maps1){
                    if(m1.get("name").equals(m2.get("name"))){
                        if(m1.get("from_id")==null){
                            maps2.add(m2);
                            break;
                        }else if(m2.get("from_id")==null){
                            maps2.add(m1);
                            break;
                        }else{
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String createdtime1 = m1.get("createdtime").toString();
                            String createdtime2 = m2.get("createdtime").toString();
                            long time1 =0;
                            long time2 =0;
                            try {
                                time1 = format.parse(createdtime1).getTime();
                                time2 = format.parse(createdtime2).getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            int i = (int) (time1 - time2);
                            if(i>0){
                                maps2.add(m1);
                                break;
                            }else{
                                maps2.add(m2);
                                break;
                            }

                        }
                    }else{
                        maps2.add(m1);
                    }

                }
            }
        }else{
            for(Map<String, Object> m2:maps1){
                for(Map<String, Object> m1:maps){
                    if(m1.get("name").equals(m2.get("name"))){
                        if(m1.get("from_id")==null){
                            maps2.add(m2);
                            break;
                        }else if(m2.get("from_id")==null){
                            maps2.add(m1);
                            break;
                        }else{
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String createdtime1 = m1.get("createdtime").toString();
                            String createdtime2 = m2.get("createdtime").toString();
                            long time1 =0;
                            long time2 =0;
                            try {
                                time1 = format.parse(createdtime1).getTime();
                                time2 = format.parse(createdtime2).getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            int i = (int) (time1 - time2);
                            if(i>0){
                                maps2.add(m1);
                                break;
                            }else{
                                maps2.add(m2);
                                break;
                            }

                        }
                    }else{
                        maps2.add(m2);
                        break;
                    }

                }
            }
        }
        int count = baseMapper.count(map);
        return R.ok("成功").put("list",maps2).put("total",count);
    }

    //聊天列表（学生）
    public R stUserList(Map<String,Object> map){
        ParamUtils.formatPageParam(map);
        if(map.get("searchKey")!=null && !map.get("searchKey").equals("")){
            map.put("searchKey","%"+map.get("searchKey").toString()+"%");
        }
        List<Map<String, Object>> maps2 = new ArrayList<>();
        List<Map<String, Object>> maps = baseMapper.stUserList(map);
        List<Map<String, Object>> maps1 = baseMapper.stUserList1(map);
        int count = baseMapper.countSt(map);
        if(maps.size()==0){
            maps2=maps1;
        }
        if(maps1.size()==0){
            maps2=maps;
        }
        if(maps.size()>maps1.size()){
            for(Map<String, Object> m1:maps){
                for(Map<String, Object> m2:maps1){
                    if(m1.get("name").equals(m2.get("name"))){
                            if(m1.get("from_id")==null){
                                maps2.add(m2);
                                break;
                            }else if(m2.get("from_id")==null){
                                maps2.add(m1);
                                break;
                            }else{
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String createdtime1 = m1.get("createdtime").toString();
                                String createdtime2 = m2.get("createdtime").toString();
                                long time1 =0;
                                long time2 =0;
                                try {
                                    time1 = format.parse(createdtime1).getTime();
                                    time2 = format.parse(createdtime2).getTime();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                int i = (int) (time1 - time2);
                                if(i>0){
                                    maps2.add(m1);
                                    break;
                                }else{
                                    maps2.add(m2);
                                    break;
                                }
                        }
                    }else{
                        maps2.add(m1);
                    }

                }
            }
        }else{
            for(Map<String, Object> m2:maps1){
                for(Map<String, Object> m1:maps){
                    if(m1.get("name").equals(m2.get("name"))){
                        if(m1.get("from_id")==null){
                            maps2.add(m2);
                            break;
                        }else if(m2.get("from_id")==null){
                            maps2.add(m1);
                            break;
                        }else{
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String createdtime1 = m1.get("createdtime").toString();
                            String createdtime2 = m2.get("createdtime").toString();
                            long time1 =0;
                            long time2 =0;
                            try {
                                time1 = format.parse(createdtime1).getTime();
                                time2 = format.parse(createdtime2).getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            int i = (int) (time1 - time2);
                            if(i>0){
                                maps2.add(m1);
                                break;
                            }else{
                                maps2.add(m2);
                                break;
                            }
                        }
                    }
                    if(maps.size()-1 == maps.indexOf(m1)){
                        maps2.add(m2);
                        break;
                    }

                }
            }
        }

        List<Map<String, Object>> user = baseMapper.user();
        if(maps2.size()==0){
            return R.ok("成功").put("list",user).put("total",user.size());
        }
        if(user.size()>maps2.size()){
            for(Map<String, Object>  us:user){
                for(Map<String, Object> mp:maps2){
                    if(!us.get("name").equals(mp.get("name"))){
                        maps2.add(us);
                        break;
                    }
                }
            }
        }

        return R.ok("成功").put("list",maps2).put("total",count);
    }
}
