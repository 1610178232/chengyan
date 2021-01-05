package com.example.psycholog.module.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.psycholog.module.dao.MhUserDao;
import com.example.psycholog.module.entity.MhUserEntity;
import com.example.psycholog.module.until.ParamUtils;
import com.example.psycholog.module.until.R;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("MhUserService")
public class MhUserService extends ServiceImpl<MhUserDao, MhUserEntity> {

    //登录
    public R login(MhUserEntity adminEntity){
        MhUserEntity userEntity = new MhUserEntity();
        userEntity.setName(adminEntity.getName());
        MhUserEntity user = baseMapper.selectOne(userEntity);
        if(user==null||user.equals("")){
            return R.error("该账号不存在");
        }
        userEntity.setPassword(adminEntity.getPassword());
        MhUserEntity user2 = baseMapper.selectOne(userEntity);
        if(user2==null||user2.equals("")){
            return R.error("密码错误");
        }
            MhUserEntity user1 = new MhUserEntity();
            user1.setToken(generateToken());
            user1.setId(user2.getId());
            baseMapper.updateById(user1);
        MhUserEntity userEntity1 = baseMapper.selectOne(user1);
        return R.ok("登录成功").put("token",userEntity1.getToken()).put("name",userEntity1.getName()).put("id",userEntity1.getId()).put("img",userEntity1.getImg());
    }

   //生成token
    private String generateToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public R getUserInfo(Map<String,Object> map){
        ParamUtils.formatPageParam(map);
        if(map.get("searchKey")!=null && !map.get("searchKey").equals("")){
            map.put("searchKey","%"+map.get("searchKey").toString()+"%");
        }
        List<MhUserEntity> userInfo = baseMapper.getUserInfo( map);
        int count = baseMapper.count(map);
        return R.ok("成功").put("list",userInfo).put("total",count);
    }

    public R addOrUpdate(MhUserEntity user){
        if(user.getId()!=null){
            baseMapper.updateById(user);
        }else{
            user.setPassword("123456");
            baseMapper.insert(user);
        }
        return R.ok("成功");
    }

    public R delUser(MhUserEntity user){
     baseMapper.deleteById(user.getId());
        return R.ok("成功");
    }

    public R getUser(MhUserEntity user){
        MhUserEntity userEntity = baseMapper.selectById(user.getId());
        return R.ok("成功").put("user",userEntity);
    }
}
