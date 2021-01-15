package com.example.admin.common.type.convertor;

import com.example.admin.common.base.IBaseType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MybatisTypeHandler<T extends IBaseType> extends BaseTypeHandler<T> {

    private Class<T> type;

    public MybatisTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T type, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, type.getValue());
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        Byte value = resultSet.getByte(columnName);
        return resultSet.wasNull() ? null : this.getTypeByValue(value);
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        Byte value = resultSet.getByte(columnIndex);
        return resultSet.wasNull() ? null : this.getTypeByValue(value);
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        Byte value = callableStatement.getByte(columnIndex);
        return callableStatement.wasNull() ? null : this.getTypeByValue(value);
    }

    private T getTypeByValue(Byte value) {
        for (T aType : this.type.getEnumConstants()) {
            if (aType.getValue()==value) {
                return aType;
            }
        }
        return null;
    }
}
