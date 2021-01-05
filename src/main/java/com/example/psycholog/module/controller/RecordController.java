package com.example.psycholog.module.controller;

import com.example.psycholog.module.entity.MhRecordEntity;
import com.example.psycholog.module.service.MhRecordService;
import com.example.psycholog.module.until.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private MhRecordService recordService;

    //聊天记录
    @PostMapping("getRecordList")
    public R getRecordList(@RequestBody Map<String,Object> map){
        return recordService.getRecordList(map);
    }

    //保存聊天信息
    @PostMapping("addRecord")
    public R addRecord(@RequestBody MhRecordEntity recordEntity){
        return recordService.addRecord(recordEntity);
    }

    //聊天列表（管理员）
    @PostMapping("getChawUser")
    public R getChawUser(@RequestBody Map<String,Object> map){
        return recordService.getChawUser(map);
    }

    //聊天列表（学生）
    @PostMapping("stUserList")
    public R stUserList(@RequestBody Map<String,Object> map){
        return recordService.stUserList(map);
    }
}
