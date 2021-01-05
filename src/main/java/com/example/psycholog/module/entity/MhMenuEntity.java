package com.example.psycholog.module.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("mh_menu")
public class MhMenuEntity {
    @TableId
    private Long id;
    private String name;
    private String Icon;
    private Integer pid;
    private String url;
    private String type;

}
