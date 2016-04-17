package cn.w28l30.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class JdbcUtils {
	private static DataSource ds;
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	static {
		Properties prop = new Properties();
		InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
		try {
			prop.load(in);
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ExceptionInInitializerError();
		}

	}

	public static DataSource getDataSource() {
		return ds;
	}

	public static Connection getConnection() throws SQLException {
		try {
			Connection conn = tl.get();
			if (conn == null) {
				conn = ds.getConnection();
				tl.set(conn);
			}
			return conn;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public static void startTransaction() {
		try {
			Connection conn = tl.get();
			if (conn == null) {
				conn = ds.getConnection();
				tl.set(conn);
			}
			conn.setAutoCommit(false);

		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public static void commitTransaction() {
		try {
			Connection conn = tl.get();
			if (conn != null) {
				conn.commit();
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public static void closeTransaction() {
		try {
			Connection conn = tl.get();
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			tl.remove();
		}
	}
}
