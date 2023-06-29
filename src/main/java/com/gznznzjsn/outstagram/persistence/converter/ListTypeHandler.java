package com.gznznzjsn.outstagram.persistence.converter;

import com.gznznzjsn.outstagram.model.exception.InternalLogicException;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@MappedTypes(List.class)
public class ListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    @SneakyThrows
    public void setNonNullParameter(
            final PreparedStatement preparedStatement,
            final int i,
            final List list,
            final JdbcType jdbcType
    ) {
        Connection connection = preparedStatement.getConnection();
        Array array = connection.createArrayOf("STRING", list.toArray());
        preparedStatement.setArray(i, array);
    }

    @Override
    @SneakyThrows
    public List<String> getNullableResult(
            final ResultSet resultSet,
            final String s
    ) {
        throw new InternalLogicException("Not implemented!");
    }

    @Override
    @SneakyThrows
    public List<String> getNullableResult(
            final ResultSet resultSet,
            final int i
    ) {
        throw new InternalLogicException("Not implemented!");
    }

    @Override
    @SneakyThrows
    public List<String> getNullableResult(
            final CallableStatement callableStatement,
            final int i
    ) {
        throw new InternalLogicException("Not implemented!");
    }

}
