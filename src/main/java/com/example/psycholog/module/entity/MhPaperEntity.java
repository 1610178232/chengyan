package com.example.psycholog.module.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mh_paper")
public class MhPaperEntity {
    @TableId
    private Long id;
    private String title;
    private Long pid1;
    private String question;
    private Long pid2;
    private String answer;
    private Double grade;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdtime;
    @TableLogic
    private Integer isDelete;
    @TableField(exist = false)
    private Integer paperStatus;

}
