package com.example.admin.common.type.convertor;

import com.example.admin.common.R.RException;
import com.example.admin.common.R.RStatus;
import com.example.admin.common.base.IBaseType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

@SuppressWarnings("unchecked")
public class TypeConverterFactory implements ConverterFactory<String, IBaseType> {

    @Override
    public <T extends IBaseType> Converter<String, T> getConverter(Class<T> typeClass) {
        return new TypeConverter(typeClass);
    }

    private static class TypeConverter<T extends IBaseType>
            implements Converter<String, T> {

        private Class<T> type;

        TypeConverter(Class<T> type) {
            this.type = type;
        }

        @Override
        public T convert(String source) {
            for (T aType : this.type.getEnumConstants()) {
                if (String.valueOf(aType.getValue()).equals(source)) {
                    return aType;
                }
            }
            throw new RException(RStatus.INVALID_REQUEST_PARAMETERS);
        }
    }
}
