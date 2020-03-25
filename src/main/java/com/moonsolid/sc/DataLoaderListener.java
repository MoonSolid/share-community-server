package com.moonsolid.sc;

import java.io.InputStream;
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
import com.moonsolid.sc.dao.mariadb.BoardDaoImpl;
import com.moonsolid.sc.dao.mariadb.MemberDaoImpl;
import com.moonsolid.sc.dao.mariadb.PhotoBoardDaoImpl;
import com.moonsolid.sc.dao.mariadb.PhotoFileDaoImpl;
import com.moonsolid.sc.dao.mariadb.PlanDaoImpl;
import com.moonsolid.sc.service.impl.BoardServiceImpl;
import com.moonsolid.sc.service.impl.MemberServiceImpl;
import com.moonsolid.sc.service.impl.PhotoBoardServiceImpl;
import com.moonsolid.sc.service.impl.PlanServiceImpl;
import com.moonsolid.sql.PlatformTransactionManager;
import com.moonsolid.sql.SqlSessionFactoryProxy;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      InputStream inputStream = Resources.getResourceAsStream(//
          "com/moonsolid/sc/conf/mybatis-config.xml");

      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy( //
          new SqlSessionFactoryBuilder().build(inputStream));
      context.put("sqlSessionFactory", sqlSessionFactory);


      BoardDao boardDao = new BoardDaoImpl(sqlSessionFactory);
      PlanDao planDao = new PlanDaoImpl(sqlSessionFactory);
      MemberDao memberDao = new MemberDaoImpl(sqlSessionFactory);
      PhotoBoardDao photoBoardDao = new PhotoBoardDaoImpl(sqlSessionFactory);
      PhotoFileDao photoFileDao = new PhotoFileDaoImpl(sqlSessionFactory);

      PlatformTransactionManager txManager = //
          new PlatformTransactionManager(sqlSessionFactory);

      context.put("planService", new PlanServiceImpl(planDao));
      context.put("photoBoardService", //
          new PhotoBoardServiceImpl(txManager, photoBoardDao, photoFileDao));
      context.put("boardService", new BoardServiceImpl(boardDao));
      context.put("memberService", new MemberServiceImpl(memberDao));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

  }

}
