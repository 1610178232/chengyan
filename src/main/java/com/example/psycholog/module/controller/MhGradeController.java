package com.example.psycholog.module.controller;

import com.example.psycholog.module.service.MhGradeService;
import com.example.psycholog.module.until.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/grade")
public class MhGradeController {
    @Autowired
    private MhGradeService gradeService;

    /**分数列表*/
    @PostMapping("/gradeList")
    public R gradeList(@RequestBody Map<String,Object> map){
        return gradeService.gradeList(map);
    }

    @PostMapping("/addGrade")
    public R addGrade(@RequestBody Map<String,Object> map){
        return gradeService.add(map);
    }

    /**分数列表(学生)*/
    @PostMapping("/gradeStList")
    public R gradeStList(@RequestBody Map<String,Object> map){
        return gradeService.gradeStList(map);
    }

}
