package com.ssafy.happyhouse.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	
	public static Connection getConnection() throws SQLException {
		DataSource dataSourse = null;
		try {
			Context iCtx = new InitialContext();
			Context ctx = (Context) iCtx.lookup("java:comp/env");
			dataSourse = (DataSource) ctx.lookup("jdbc/ssafy");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return dataSourse.getConnection();
	}

	public static void close(AutoCloseable c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
