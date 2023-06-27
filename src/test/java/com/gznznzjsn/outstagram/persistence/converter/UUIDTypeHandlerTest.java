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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UUIDTypeHandlerTest {

    @InjectMocks
    private UUIDTypeHandler handler;

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
        var id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        int i = 1;
        handler.setNonNullParameter(preparedStatement, i, id, jdbcType);
        verify(preparedStatement).setString(i, String.valueOf(id));
        verifyNoInteractions(jdbcType);
    }

    @Test
    void setNonNullParameterWithException() throws SQLException {
        var id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        int i = 1;
        doThrow(SQLException.class)
                .when(preparedStatement).setString(i, id.toString());
        assertThrows(
                SQLException.class,
                () -> handler.setNonNullParameter(
                        preparedStatement,
                        i, id, jdbcType
                )
        );
        verify(preparedStatement).setString(i, String.valueOf(id));
        verifyNoInteractions(jdbcType);
    }

    @Test
    void getNullNullableResultFromResultSetByName() throws SQLException {
        String s = "COLUMN NAME";
        when(resultSet.getString(s)).thenReturn(null);
        UUID result = handler.getNullableResult(resultSet, s);
        assertNull(result);
        verify(resultSet).getString(s);
    }

    @Test
    void getNotNullNullableResultFromResultSetByName()
            throws SQLException {
        String s = "COLUMN NAME";
        var id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        when(resultSet.getString(s)).thenReturn(id.toString());
        UUID result = handler.getNullableResult(resultSet, s);
        assertEquals(id, result);
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
        UUID result = handler.getNullableResult(resultSet, i);
        assertNull(result);
        verify(resultSet).getString(i);
    }

    @Test
    void getNotNullNullableResultFromResultSetByIndex()
            throws SQLException {
        int i = 1;
        var id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        when(resultSet.getString(i)).thenReturn(id.toString());
        UUID result = handler.getNullableResult(resultSet, i);
        assertEquals(id, result);
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
        UUID result = handler.getNullableResult(callableStatement, i);
        assertNull(result);
        verify(callableStatement).getString(i);
    }

    @Test
    void getNotNullNullableResultFromCallableStatementByIndex()
            throws SQLException {
        int i = 1;
        var id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        when(callableStatement.getString(i)).thenReturn(id.toString());
        UUID result = handler.getNullableResult(callableStatement, i);
        assertEquals(id, result);
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
