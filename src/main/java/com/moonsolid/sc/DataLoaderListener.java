package com.moonsolid.sc;

import java.util.Map;
import com.moonsolid.sc.context.ApplicationContextListener;
import com.moonsolid.sc.dao.mariadb.BoardDaoImpl;
import com.moonsolid.sc.dao.mariadb.MemberDaoImpl;
import com.moonsolid.sc.dao.mariadb.PhotoBoardDaoImpl;
import com.moonsolid.sc.dao.mariadb.PhotoFileDaoImpl;
import com.moonsolid.sc.dao.mariadb.PlanDaoImpl;
import com.moonsolid.sql.DataSource;
import com.moonsolid.sql.PlatformTransactionManager;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      String jdbcUrl = "jdbc:mariadb://localhost:3306/scdb";
      String username = "study";
      String password = "1111";


      DataSource dataSource = new DataSource(//
          jdbcUrl, username, password);
      context.put("dataSource", dataSource);

      context.put("boardDao", new BoardDaoImpl(dataSource));
      context.put("planDao", new PlanDaoImpl(dataSource));
      context.put("memberDao", new MemberDaoImpl(dataSource));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(dataSource));
      context.put("photoFileDao", new PhotoFileDaoImpl(dataSource));

      PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);
      context.put("transactionManager", txManager);


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

    DataSource dataSource = (DataSource) context.get("dataSource");
    dataSource.clean();
  }

}
