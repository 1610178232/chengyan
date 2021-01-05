package com.example.psycholog.module.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("mh_grade_record")
public class MhGradeRecordEntity {
    @TableId
    private Long id;
    private Long paperId;
    private Long userId;
    private Long answerId;
    private Long questionId;
}
