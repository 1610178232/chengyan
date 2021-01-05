package com.example.psycholog.module.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("mh_user")
public class MhUserEntity {
    @TableId
    private Long id;
    private String name;
    private String studentNumber;
    private String img;
    private String password;
    private Integer sex;
    private String mobile;
    private String userClass;
    private String yard;
    private String type;
    @TableLogic
    private Integer isDelete;
    private String token;
}
