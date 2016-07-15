package com.sql;

import java.lang.reflect.Field;
import java.sql.SQLException;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import oracle.sql.BLOB;
import oracle.sql.DATE;

public class DbUtil {

	protected JdbcTemplate dbop;
	public JdbcTemplate getDbop() {
		return dbop;
	}
	public void setDbop(JdbcTemplate dbop) {
		this.dbop = dbop;
	}
	public String nextHex(String tabName,String colName) throws SQLException{
		String maxSeq = "1";
		String mid = null;
		try {
			int maxInt = 0;
			mid = dbop.queryForObject("SELECT MAX(TO_NUMBER(" + colName + ",'XXXXXXXX')) " + colName + " FROM " + tabName,String.class);
			if(mid != null){
				maxSeq = mid;
				maxSeq = maxSeq == null?"0":maxSeq;
				maxInt = Integer.valueOf(maxSeq);
				maxInt++;
				maxSeq = Integer.toString(maxInt,16);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxSeq;
	}
	public String nextHex(String tabName,String colName,String where) throws SQLException{
		String maxSeq = "1";
		String mid = null;
		try {
			int maxInt = 0;
			mid = dbop.queryForObject("SELECT MAX(TO_NUMBER(" + colName + ",'XXXXXXXX')) " + colName + " FROM " + tabName + " WHERE " + where,String.class);
			if(mid != null){
				maxSeq = mid;
				maxSeq = maxSeq == null?"0":maxSeq;
				maxInt = Integer.valueOf(maxSeq);
				maxInt++;
				maxSeq = Integer.toString(maxInt,16);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxSeq;
	}
	public int next(String tabName,String colName) throws SQLException{
		int maxSeq = 0;
		try {
			maxSeq = dbop.queryForObject("SELECT MAX(" + colName + ") " + colName + " FROM " + tabName,Integer.class);
			maxSeq++;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxSeq;
	}
	public int next(String tabName,String colName,String where) throws SQLException{
		int maxSeq = 0;
		SqlRowSet rowSet = null;
		try {
			rowSet = dbop.queryForRowSet("SELECT MAX(" + colName + ") " + colName + " FROM " + tabName + " WHERE " + where);
			maxSeq = rowSet.getInt(colName);
			maxSeq++;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxSeq;
	}

}
