package com.example.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("admin")
public class AdminPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String username;

    private String password;

    private String rememberToken;

    private String realname;

    private Boolean flag;

    private Boolean disabled;

    private Long roleId;

    private Long lastTime;

    private String remark;

    private Long createdAt;

    private Long updatedAt;

    @TableField(exist = false)
    private RolePo rolePo;

}
