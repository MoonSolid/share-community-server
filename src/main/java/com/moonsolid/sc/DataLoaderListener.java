package com.moonsolid.sc;

import java.sql.Connection;
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
      String jdbcUrl = "jdbc:mariadb://localhost:3306/scdb";
      String username = "study";
      String password = "1111";


      context.put("boardDao", new BoardDaoImpl(jdbcUrl, username, password));
      context.put("planDao", new PlanDaoImpl(jdbcUrl, username, password));
      context.put("memberDao", new MemberDaoImpl(jdbcUrl, username, password));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(jdbcUrl, username, password));
      context.put("photoFileDao", new PhotoFileDaoImpl(jdbcUrl, username, password));


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}

}
