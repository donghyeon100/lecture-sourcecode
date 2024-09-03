package me.bdh.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import me.bdh.dto.BoardDTO;
import oracle.jdbc.driver.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class BoardListTypeHandler extends BaseTypeHandler<List<BoardDTO>> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<BoardDTO> parameter, JdbcType jdbcType)
			throws SQLException {

 		if(ps.getConnection() instanceof OracleConnection) {
 			OracleConnection connection = ps.getConnection().unwrap(OracleConnection.class);
 			ArrayDescriptor desc = ArrayDescriptor.createDescriptor("BOARD_DTO_TABLE", connection);
 			ARRAY array = new ARRAY(desc, connection, parameter.toArray());
 			ps.setArray(i, array);
 		}
		
	}

	@Override
	public List<BoardDTO> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		throw new UnsupportedOperationException("Not supported");
	}

	@Override
	public List<BoardDTO> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		throw new UnsupportedOperationException("Not supported");
	}

	@Override
	public List<BoardDTO> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		throw new UnsupportedOperationException("Not supported");
	}

	
	
}
