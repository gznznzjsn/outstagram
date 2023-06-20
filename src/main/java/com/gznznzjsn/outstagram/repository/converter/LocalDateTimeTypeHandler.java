package com.gznznzjsn.outstagram.repository.converter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@MappedTypes(LocalDateTime.class)
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, String.valueOf(localDateTime));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getObject(s, LocalDateTime.class);
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getObject(i, LocalDateTime.class);
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getObject(i, LocalDateTime.class);
    }

}