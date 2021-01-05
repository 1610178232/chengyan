package com.example.psycholog.module.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mh_grade")
public class MhGradeEntity {
    @TableId
    private Long id;
    private Long userId;
    private Long paperId;
    private Double grade;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdtime;
    @TableLogic
    private Integer isDelete;

}
