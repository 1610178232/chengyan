package com.example.psycholog.module.controller;

import com.example.psycholog.module.entity.MhPaperEntity;
import com.example.psycholog.module.service.MhPaperService;
import com.example.psycholog.module.until.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private MhPaperService paperService;

    /**问卷列表*/
    @PostMapping("/paperList")
    public R paperList(@RequestBody Map<String,Object> map){
        return paperService.paperList(map);
    }

    /**修改问卷*/
    @PostMapping("/update")
    public R update(@RequestBody MhPaperEntity paper){
        return paperService.update(paper);
    }

    /**删除问卷*/
    @PostMapping("/delPaper")
    public R delPaper(@RequestBody MhPaperEntity paper){
        return paperService.delPaper(paper);
    }
    /**问卷详情（管理员）*/
    @PostMapping("/paperInfo")
    public R paperInfo(@RequestBody Map<String,Object> map){
        return paperService.info(map);
    }
    /**添加问卷*/
    @PostMapping("/addPaper")
    public R addPaper(@RequestBody Map<String,Object> map){
        return paperService.add(map);
    }
    /**问卷详情(学生)*/
    @PostMapping("/paperInfoSt")
    public R paperInfoSt(@RequestBody Map<String,Object> map){
        return paperService.infoSt(map);
    }
}
