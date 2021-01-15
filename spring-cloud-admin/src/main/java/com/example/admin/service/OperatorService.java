package com.example.admin.service;

import com.example.admin.dto.operator.LoginDto;
import com.example.admin.po.AdminPo;
import com.example.admin.po.MenuPo;

import java.util.List;

public interface OperatorService {

    LoginDto login(String username, String password);

    void logout();

    List<MenuPo> getMenuByUserId(Long userId);

    AdminPo getUserByUsername(String username);
}
