package com.moonsolid.sc;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.moonsolid.sc.context.ApplicationContextListener;
import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.dao.MemberDao;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.dao.PhotoFileDao;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sql.MybatisDaoFactory;
import com.moonsolid.sql.PlatformTransactionManager;
import com.moonsolid.sql.SqlSessionFactoryProxy;
import com.moonsolid.util.ApplicationContext;

public class ContextLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      HashMap<String, Object> beans = new HashMap<>();

      InputStream inputStream = Resources.getResourceAsStream(//
          "com/moonsolid/sc/conf/mybatis-config.xml");

      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy( //
          new SqlSessionFactoryBuilder().build(inputStream));
      context.put("sqlSessionFactory", sqlSessionFactory);

      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      beans.put("boardDao", daoFactory.createDao(BoardDao.class));
      beans.put("planDao", daoFactory.createDao(PlanDao.class));
      beans.put("memberDao", daoFactory.createDao(MemberDao.class));
      beans.put("photoBoardDao", daoFactory.createDao(PhotoBoardDao.class));
      beans.put("photoFileDao", daoFactory.createDao(PhotoFileDao.class));

      PlatformTransactionManager txManager = //
          new PlatformTransactionManager(sqlSessionFactory);
      beans.put("transactionManager", txManager);

      ApplicationContext appCtx = new ApplicationContext(//
          "com.moonsolid.sc", beans);
      appCtx.printBeans();
      context.put("iocContainer", appCtx);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

  }

}
