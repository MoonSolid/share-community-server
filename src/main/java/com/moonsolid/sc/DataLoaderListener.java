package com.moonsolid.sc;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

      InputStream inputStream = Resources.getResourceAsStream(//
          "com/moonsolid/sc/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = //
          new SqlSessionFactoryBuilder().build(inputStream);

      context.put("boardDao", new BoardDaoImpl(sqlSessionFactory));
      context.put("planDao", new PlanDaoImpl(sqlSessionFactory));
      context.put("memberDao", new MemberDaoImpl(sqlSessionFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(sqlSessionFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(sqlSessionFactory));

      PlatformTransactionManager txManager = //
          new PlatformTransactionManager(dataSource);
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
