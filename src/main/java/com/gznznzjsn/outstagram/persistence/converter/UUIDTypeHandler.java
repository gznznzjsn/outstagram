package com.gznznzjsn.outstagram.persistence.converter;

import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

@MappedTypes(UUID.class)
public class UUIDTypeHandler extends BaseTypeHandler<UUID> {

    @Override
    @SneakyThrows
    public void setNonNullParameter(
            final PreparedStatement preparedStatement,
            final int i,
            final UUID uuid,
            final JdbcType jdbcType) {
        preparedStatement.setString(i, uuid.toString());
    }

    @Override
    @SneakyThrows
    public UUID getNullableResult(
            final ResultSet resultSet,
            final String s
    ) {
        String value = resultSet.getString(s);
        if (value == null) {
            return null;
        }
        return UUID.fromString(value);
    }

    @Override
    @SneakyThrows
    public UUID getNullableResult(
            final ResultSet resultSet,
            final int i
    ) {
        String value = resultSet.getString(i);
        if (value == null) {
            return null;
        }
        return UUID.fromString(value);
    }

    @Override
    @SneakyThrows
    public UUID getNullableResult(
            final CallableStatement callableStatement,
            final int i
    ) {
        String value = callableStatement.getString(i);
        if (value == null) {
            return null;
        }
        return UUID.fromString(value);
    }

}
