package com.example.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.admin.po.MenuPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuDao extends BaseMapper<MenuPo> {

    List<MenuPo> selectListByUserId(@Param("userId") Long userId);
}
