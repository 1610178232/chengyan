package com.example.psycholog.module.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mh_record")
public class MhRecordEntity {
    @TableId
    private Long id;
    private Integer fromId;
    private Integer toId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdtime;
    private Integer status;
    @TableLogic
    private Integer isDelete;
}
