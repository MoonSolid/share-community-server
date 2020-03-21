package com.moonsolid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import com.moonsolid.sql.ConnectionProxy;

public class ConnectionFactory {

  String jdbcUrl;
  String username;
  String password;

  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

  public ConnectionFactory(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection() throws Exception {
    Connection con = connectionLocal.get();

    if (con != null) {
      return con;
    }

    con = new ConnectionProxy(DriverManager.getConnection(//
        jdbcUrl, username, password));


    connectionLocal.set(con);
    return con;
  }

  public Connection removeConnection() {
    Connection con = connectionLocal.get();
    if (con != null) {
      connectionLocal.remove();
    }
    return con;
  }
}
