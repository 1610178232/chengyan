package com.example.psycholog.module.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.psycholog.module.dao.MhMenuDao;
import com.example.psycholog.module.entity.MhMenuEntity;
import com.example.psycholog.module.until.R;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MhMenuService extends ServiceImpl<MhMenuDao, MhMenuEntity> {

    public R menuList(Map<String, Object> map){
        String token = map.get("token").toString();
        String type = baseMapper.getType(token);
        HashMap<String, Object> param = new HashMap<>();
        param.put("type",type);
        param.put("pid",null);
        List<Map<String, Object>> maps = baseMapper.menuList(param);
        for (Map<String, Object> list:maps){
            param.put("pid",list.get("id"));
            List<Map<String, Object>> children = baseMapper.menuList1(param);
            list.put("children",children);
        }
        return R.ok("成功").put("maps",maps);
    };

}
