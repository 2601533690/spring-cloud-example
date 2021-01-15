package com.example.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("role")
public class RolePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String name;

    private String lastModified;

    private Long createdAt;

    private Long updatedAt;

    @TableField(exist = false)
    private List<MenuPo> menuPoList;

    @TableField(exist = false)
    private AdminPo adminPo;

    @TableField(exist = false)
    private Long adminNumber;

}
