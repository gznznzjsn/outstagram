package com.gznznzjsn.outstagram.persistence.converter;

import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

@MappedTypes(LocalDateTime.class)
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    @SneakyThrows
    public void setNonNullParameter(
            final PreparedStatement preparedStatement,
            final int i,
            final LocalDateTime localDateTime,
            final JdbcType jdbcType
    ) {
        preparedStatement.setString(i, String.valueOf(localDateTime));
    }

    @Override
    @SneakyThrows
    public LocalDateTime getNullableResult(
            final ResultSet resultSet,
            final String s
    ) {
        String value = resultSet.getString(s);
        if (value == null) {
            return null;
        }
        return LocalDateTime.parse(value);
    }

    @Override
    @SneakyThrows
    public LocalDateTime getNullableResult(
            final ResultSet resultSet,
            final int i
    ) {
        String value = resultSet.getString(i);
        if (value == null) {
            return null;
        }
        return LocalDateTime.parse(value);
    }

    @Override
    @SneakyThrows
    public LocalDateTime getNullableResult(
            final CallableStatement callableStatement,
            final int i
    ) {
        String value = callableStatement.getString(i);
        if (value == null) {
            return null;
        }
        return LocalDateTime.parse(value);
    }

}
