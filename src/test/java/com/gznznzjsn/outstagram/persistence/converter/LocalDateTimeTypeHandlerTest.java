package com.gznznzjsn.outstagram.persistence.converter;

import org.apache.ibatis.type.JdbcType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalDateTimeTypeHandlerTest {

    @InjectMocks
    private LocalDateTimeTypeHandler handler;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private JdbcType jdbcType;

    @Mock
    private ResultSet resultSet;

    @Mock
    private CallableStatement callableStatement;

    @Test
    void setNonNullParameter() throws SQLException {
        var time = LocalDateTime.of(1, 1, 1, 1, 1);
        int i = 1;
        handler.setNonNullParameter(preparedStatement, i, time, jdbcType);
        verify(preparedStatement).setString(i, String.valueOf(time));
        verifyNoInteractions(jdbcType);
    }

    @Test
    void setNonNullParameterWithException() throws SQLException {
        var time = LocalDateTime.of(1, 1, 1, 1, 1);
        int i = 1;
        doThrow(SQLException.class)
                .when(preparedStatement).setString(i, time.toString());
        assertThrows(
                SQLException.class,
                () -> handler.setNonNullParameter(
                        preparedStatement,
                        i, time, jdbcType
                )
        );
        verify(preparedStatement).setString(i, String.valueOf(time));
        verifyNoInteractions(jdbcType);
    }

    @Test
    void getNullNullableResultFromResultSetByName() throws SQLException {
        String s = "COLUMN NAME";
        when(resultSet.getString(s)).thenReturn(null);
        LocalDateTime result = handler.getNullableResult(resultSet, s);
        assertNull(result);
        verify(resultSet).getString(s);
    }

    @Test
    void getNotNullNullableResultFromResultSetByName() throws SQLException {
        String s = "COLUMN NAME";
        var time = LocalDateTime.of(1, 1, 1, 1, 1);
        when(resultSet.getString(s)).thenReturn(time.toString());
        LocalDateTime result = handler.getNullableResult(resultSet, s);
        assertEquals(time, result);
        verify(resultSet).getString(s);
    }

    @Test
    void getNullableResultFromResultSetByNameWithException()
            throws SQLException {
        String s = "COLUMN NAME";
        when(resultSet.getString(s)).thenThrow(SQLException.class);
        assertThrows(
                SQLException.class,
                () -> handler.getNullableResult(resultSet, s)
        );
        verify(resultSet).getString(s);
    }

    @Test
    void getNullNullableResultFromResultSetByIndex() throws SQLException {
        int i = 1;
        when(resultSet.getString(i)).thenReturn(null);
        LocalDateTime result = handler.getNullableResult(resultSet, i);
        assertNull(result);
        verify(resultSet).getString(i);
    }

    @Test
    void getNotNullNullableResultFromResultSetByIndex() throws SQLException {
        int i = 1;
        var time = LocalDateTime.of(1, 1, 1, 1, 1);
        when(resultSet.getString(i)).thenReturn(time.toString());
        LocalDateTime result = handler.getNullableResult(resultSet, i);
        assertEquals(time, result);
        verify(resultSet).getString(i);
    }

    @Test
    void getNullableResultFromResultSetByIndexWithException()
            throws SQLException {
        int i = 1;
        when(resultSet.getString(i)).thenThrow(SQLException.class);
        assertThrows(
                SQLException.class,
                () -> handler.getNullableResult(resultSet, i)
        );
        verify(resultSet).getString(i);
    }

    @Test
    void getNullNullableResultFromCallableStatementByIndex()
            throws SQLException {
        int i = 1;
        when(callableStatement.getString(i)).thenReturn(null);
        LocalDateTime result = handler.getNullableResult(callableStatement, i);
        assertNull(result);
        verify(callableStatement).getString(i);
    }

    @Test
    void getNotNullNullableResultFromCallableStatementByIndex()
            throws SQLException {
        int i = 1;
        var time = LocalDateTime.of(1, 1, 1, 1, 1);
        when(callableStatement.getString(i)).thenReturn(time.toString());
        LocalDateTime result = handler.getNullableResult(callableStatement, i);
        assertEquals(time, result);
        verify(callableStatement).getString(i);
    }

    @Test
    void getNullableResultFromCallableStatementByIndexWithException()
            throws SQLException {
        int i = 1;
        when(callableStatement.getString(i)).thenThrow(SQLException.class);
        assertThrows(
                SQLException.class,
                () -> handler.getNullableResult(callableStatement, i)
        );
        verify(callableStatement).getString(i);
    }

}
