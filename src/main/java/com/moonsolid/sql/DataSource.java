package com.moonsolid.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DataSource {
  String jdbcUrl;
  String username;
  String password;

  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

  ArrayList<Connection> conList = new ArrayList<>();

  public DataSource(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection() throws Exception {

    Connection con = connectionLocal.get();
    if (con != null) {
      return con;
    }

    if (conList.size() > 0) {

      con = conList.remove(0);

    } else {
      con = new ConnectionProxy(DriverManager.getConnection( //
          jdbcUrl, //
          username, //
          password));
    }

    connectionLocal.set(con);

    System.out.printf("DataSource: 현재 보관중인 객체 %d개\n", conList.size());

    return con;
  }

  public Connection removeConnection() {
    Connection con = connectionLocal.get();
    if (con != null) {
      connectionLocal.remove();

      conList.add(con);
    }
    System.out.printf("DataSource: 현재 보관중인 객체 %d개\n", conList.size());
    return con;
  }

  public void clean() {
    while (conList.size() > 0) {
      try {
        ((ConnectionProxy) conList.remove(0)).realClose();
      } catch (Exception e) {
      }
    }
  }
}
