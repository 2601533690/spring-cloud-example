package com.example.admin.converter;

import com.example.admin.dto.operator.UserMenuListDto;
import com.example.admin.po.MenuPo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class OperatorConverterImpl implements OperatorConverter {

    @Override
    public UserMenuListDto converterToUserMenuListDto(MenuPo menuPo) {
        if ( menuPo == null ) {
            return null;
        }

        UserMenuListDto userMenuListDto = new UserMenuListDto();

        return userMenuListDto;
    }

    @Override
    public List<UserMenuListDto> converterToUserMenuListDtoList(List<MenuPo> menuPoList) {
        if ( menuPoList == null ) {
            return null;
        }

        List<UserMenuListDto> list = new ArrayList<UserMenuListDto>( menuPoList.size() );
        for ( MenuPo menuPo : menuPoList ) {
            list.add( converterToUserMenuListDto( menuPo ) );
        }

        return list;
    }
}
