package com.example.admin.converter;

import com.example.admin.dto.operator.UserMenuListDto;
import com.example.admin.po.MenuPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperatorConverter {

    @Mappings({
            @Mapping(target = "childMenuList", ignore = true)
    })
    UserMenuListDto converterToUserMenuListDto(MenuPo menuPo);

    List<UserMenuListDto> converterToUserMenuListDtoList(List<MenuPo> menuPoList);
}
