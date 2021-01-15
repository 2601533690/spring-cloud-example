package com.example.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admin.po.AdminPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface AdminDao extends BaseMapper<AdminPo> {

    AdminPo selectByUsername(@Param("username") String username);

}
