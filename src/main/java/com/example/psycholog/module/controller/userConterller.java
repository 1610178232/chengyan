package com.example.psycholog.module.controller;

import com.example.psycholog.module.entity.MhMenuEntity;
import com.example.psycholog.module.entity.MhUserEntity;
import com.example.psycholog.module.service.MhMenuService;
import com.example.psycholog.module.service.MhUserService;
import com.example.psycholog.module.until.FileUtils;
import com.example.psycholog.module.until.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class userConterller {

    @Autowired
    private MhMenuService mhMenuService;
    @Autowired
    private MhUserService userService;

    /**登录*/
    @PostMapping("login")
    public R login(@RequestBody MhUserEntity userEntity ){
        return userService.login(userEntity);
    }
    /**菜单*/
    @PostMapping("/menu")
    public R menuList(@RequestBody Map<String,Object> map){
        return mhMenuService.menuList(map);
    }
    /**用户信息*/
    @PostMapping("/getUserInfo")
    public R getUserInfo(@RequestBody Map<String,Object> map){
        return userService.getUserInfo(map);
    }

    /**修改或添加用户*/
    @PostMapping("/addOrUpdate")
    public R addOrUpdate(@RequestBody MhUserEntity user){
        return userService.addOrUpdate(user);
    }

    /**删除用户*/
    @PostMapping("/delUser")
    public R delUser(@RequestBody MhUserEntity user){
        return userService.delUser(user);
    }

    @PostMapping("/getUser")
    public R getUser(@RequestBody MhUserEntity user){
        return userService.getUser(user);
    }

    /**单张图片上传*/
    @PostMapping("uploadFile")
    public R uploadFile(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return R.error("上传文件不能为空");
        }
        return FileUtils.uploadFileUt(file);
    }
}
