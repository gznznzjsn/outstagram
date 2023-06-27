package com.gznznzjsn.outstagram.persistence.converter;

import lombok.SneakyThrows;
import org.apache.ibatis.type.JdbcType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UUIDTypeHandlerTest {

    @InjectMocks
    private UUIDTypeHandler handler;

    @Test
    @SneakyThrows
    void setNonNullParameter() {
        var statement = mock(PreparedStatement.class);
        var id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        int i = 1;
        var jdbcType = mock(JdbcType.class);
        handler.setNonNullParameter(statement, i, id, jdbcType);
        verify(statement).setString(i, String.valueOf(id));
        verifyNoInteractions(jdbcType);
    }

    @Test
    @SneakyThrows
    void setNonNullParameterWithException() {
        var statement = mock(PreparedStatement.class);
        var id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        int i = 1;
        var jdbcType = mock(JdbcType.class);
        doThrow(SQLException.class)
                .when(statement).setString(i, id.toString());
        assertThrows(
                SQLException.class,
                () -> handler.setNonNullParameter(statement, i, id, jdbcType)
        );
        verify(statement).setString(i, String.valueOf(id));
        verifyNoInteractions(jdbcType);
    }

    @Test
    @SneakyThrows
    void getNullNullableResultFromResultSetByName() {
        var resultSet = mock(ResultSet.class);
        String s = "COLUMN NAME";
        when(resultSet.getString(s)).thenReturn(null);
        UUID result = handler.getNullableResult(resultSet, s);
        assertNull(result);
        verify(resultSet).getString(s);
    }

    @Test
    @SneakyThrows
    void getNotNullNullableResultFromResultSetByName() {
        var resultSet = mock(ResultSet.class);
        String s = "COLUMN NAME";
        var id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        when(resultSet.getString(s)).thenReturn(id.toString());
        UUID result = handler.getNullableResult(resultSet, s);
        assertEquals(id, result);
        verify(resultSet).getString(s);
    }

    @Test
    @SneakyThrows
    void getNullableResultFromResultSetByNameWithException() {
        var resultSet = mock(ResultSet.class);
        String s = "COLUMN NAME";
        when(resultSet.getString(s)).thenThrow(SQLException.class);
        assertThrows(
                SQLException.class,
                () -> handler.getNullableResult(resultSet, s)
        );
        verify(resultSet).getString(s);
    }

    @Test
    @SneakyThrows
    void getNullNullableResultFromResultSetByIndex() {
        var resultSet = mock(ResultSet.class);
        int i = 1;
        when(resultSet.getString(i)).thenReturn(null);
        UUID result = handler.getNullableResult(resultSet, i);
        assertNull(result);
        verify(resultSet).getString(i);
    }

    @Test
    @SneakyThrows
    void getNotNullNullableResultFromResultSetByIndex() {
        var resultSet = mock(ResultSet.class);
        int i = 1;
        var id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        when(resultSet.getString(i)).thenReturn(id.toString());
        UUID result = handler.getNullableResult(resultSet, i);
        assertEquals(id, result);
        verify(resultSet).getString(i);
    }

    @Test
    @SneakyThrows
    void getNullableResultFromResultSetByIndexWithException() {
        var resultSet = mock(ResultSet.class);
        int i = 1;
        when(resultSet.getString(i)).thenThrow(SQLException.class);
        assertThrows(
                SQLException.class,
                () -> handler.getNullableResult(resultSet, i)
        );
        verify(resultSet).getString(i);
    }

    @Test
    @SneakyThrows
    void getNullNullableResultFromCallableStatementByIndex() {
        var statement = mock(CallableStatement.class);
        int i = 1;
        when(statement.getString(i)).thenReturn(null);
        UUID result = handler.getNullableResult(statement, i);
        assertNull(result);
        verify(statement).getString(i);
    }

    @Test
    @SneakyThrows
    void getNotNullNullableResultFromCallableStatementByIndex() {
        var statement = mock(CallableStatement.class);
        int i = 1;
        var id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        when(statement.getString(i)).thenReturn(id.toString());
        UUID result = handler.getNullableResult(statement, i);
        assertEquals(id, result);
        verify(statement).getString(i);
    }

    @Test
    @SneakyThrows
    void getNullableResultFromCallableStatementByIndexWithException(
    ) {
        var statement = mock(CallableStatement.class);
        int i = 1;
        when(statement.getString(i)).thenThrow(SQLException.class);
        assertThrows(
                SQLException.class,
                () -> handler.getNullableResult(statement, i)
        );
        verify(statement).getString(i);
    }

}
