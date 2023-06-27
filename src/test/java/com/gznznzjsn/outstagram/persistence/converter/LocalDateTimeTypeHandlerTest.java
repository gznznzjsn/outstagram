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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalDateTimeTypeHandlerTest {

    @InjectMocks
    private LocalDateTimeTypeHandler handler;

    @Test
    @SneakyThrows
    public void setNonNullParameter() {
        var statement = mock(PreparedStatement.class);
        var time = LocalDateTime.of(1, 1, 1, 1, 1);
        int i = 1;
        var jdbcType = mock(JdbcType.class);
        handler.setNonNullParameter(statement, i, time, jdbcType);
        verify(statement).setString(i, String.valueOf(time));
        verifyNoInteractions(jdbcType);
    }

    @Test
    @SneakyThrows
    public void setNonNullParameterWithException() {
        var statement = mock(PreparedStatement.class);
        var time = LocalDateTime.of(1, 1, 1, 1, 1);
        int i = 1;
        var jdbcType = mock(JdbcType.class);
        doThrow(SQLException.class)
                .when(statement).setString(i, time.toString());
        assertThrows(
                SQLException.class,
                () -> handler.setNonNullParameter(statement, i, time, jdbcType)
        );
        verify(statement).setString(i, String.valueOf(time));
        verifyNoInteractions(jdbcType);
    }

    @Test
    @SneakyThrows
    public void getNullNullableResultFromResultSetByName() {
        var resultSet = mock(ResultSet.class);
        String s = "COLUMN NAME";
        when(resultSet.getString(s)).thenReturn(null);
        LocalDateTime result = handler.getNullableResult(resultSet, s);
        assertNull(result);
        verify(resultSet).getString(s);
    }

    @Test
    @SneakyThrows
    public void getNotNullNullableResultFromResultSetByName() {
        var resultSet = mock(ResultSet.class);
        String s = "COLUMN NAME";
        var time = LocalDateTime.of(1, 1, 1, 1, 1);
        when(resultSet.getString(s)).thenReturn(time.toString());
        LocalDateTime result = handler.getNullableResult(resultSet, s);
        assertEquals(time, result);
        verify(resultSet).getString(s);
    }

    @Test
    @SneakyThrows
    public void getNullableResultFromResultSetByNameWithException() {
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
    public void getNullNullableResultFromResultSetByIndex() {
        var resultSet = mock(ResultSet.class);
        int i = 1;
        when(resultSet.getString(i)).thenReturn(null);
        LocalDateTime result = handler.getNullableResult(resultSet, i);
        assertNull(result);
        verify(resultSet).getString(i);
    }

    @Test
    @SneakyThrows
    public void getNotNullNullableResultFromResultSetByIndex() {
        var resultSet = mock(ResultSet.class);
        int i = 1;
        var time = LocalDateTime.of(1, 1, 1, 1, 1);
        when(resultSet.getString(i)).thenReturn(time.toString());
        LocalDateTime result = handler.getNullableResult(resultSet, i);
        assertEquals(time, result);
        verify(resultSet).getString(i);
    }

    @Test
    @SneakyThrows
    public void getNullableResultFromResultSetByIndexWithException() {
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
    public void getNullNullableResultFromCallableStatementByIndex() {
        var statement = mock(CallableStatement.class);
        int i = 1;
        when(statement.getString(i)).thenReturn(null);
        LocalDateTime result = handler.getNullableResult(statement, i);
        assertNull(result);
        verify(statement).getString(i);
    }

    @Test
    @SneakyThrows
    public void getNotNullNullableResultFromCallableStatementByIndex() {
        var statement = mock(CallableStatement.class);
        int i = 1;
        var time = LocalDateTime.of(1, 1, 1, 1, 1);
        when(statement.getString(i)).thenReturn(time.toString());
        LocalDateTime result = handler.getNullableResult(statement, i);
        assertEquals(time, result);
        verify(statement).getString(i);
    }

    @Test
    @SneakyThrows
    public void getNullableResultFromCallableStatementByIndexWithException() {
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
