package com.mmt.trippathon.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBConnection {

	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.driver}")
	private String jdbcDriver;
	@Value("${jdbc.username}")
	private String user;
	@Value("${jdbc.password}")
	private String password;

	private DataSource dataSource;
	private Connection connection = null;

	public Connection getDBConnection() throws SQLException {

		if (null == dataSource) {
			BasicDataSource source = new BasicDataSource();
			source.setDriverClassName(jdbcDriver);
			source.setUrl(jdbcUrl);
			source.setUsername(user);
			source.setPassword(password);
			dataSource = source;
			return dataSource.getConnection();
		}
		if (null == connection || connection.isClosed()) {
			return dataSource.getConnection();
		}
		return connection;
	}
}
