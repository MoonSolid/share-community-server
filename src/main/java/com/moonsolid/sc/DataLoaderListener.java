package com.moonsolid.sc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import com.moonsolid.sc.context.ApplicationContextListener;
import com.moonsolid.sc.dao.mariadb.BoardDaoImpl;
import com.moonsolid.sc.dao.mariadb.MemberDaoImpl;
import com.moonsolid.sc.dao.mariadb.PhotoBoardDaoImpl;
import com.moonsolid.sc.dao.mariadb.PhotoFileDaoImpl;
import com.moonsolid.sc.dao.mariadb.PlanDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {

  public static Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection(//
          "jdbc:mariadb://localhost:3306/scdb", "study", "1111");

      context.put("boardDao", new BoardDaoImpl(con));
      context.put("planDao", new PlanDaoImpl(con));
      context.put("memberDao", new MemberDaoImpl(con));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(con));
      context.put("photoFileDao", new PhotoFileDaoImpl(con));


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

    try {
      con.close();
    } catch (Exception e) {

    }
  }
}
