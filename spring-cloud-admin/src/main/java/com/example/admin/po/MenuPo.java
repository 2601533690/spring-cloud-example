package com.example.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("menu")
public class MenuPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private Long parentId;

    private Integer level;

    private String name;

    private String uri;

    private Long createdAt;

    private Long updatedAt;

    @TableField(exist = false)
    private List<Long> roleIdList;

}
