package com.moonsolid.sc;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.dao.MemberDao;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.dao.PhotoFileDao;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sql.MybatisDaoFactory;
import com.moonsolid.sql.PlatformTransactionManager;
import com.moonsolid.sql.SqlSessionFactoryProxy;

@ComponentScan(value = "com.moonsolid.sc")
public class AppConfig {
  public AppConfig() throws Exception {

  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/moonsolid/sc/conf/mybatis-config.xml");

    return new SqlSessionFactoryProxy(//
        new SqlSessionFactoryBuilder().build(inputStream));
  }

  @Bean
  public MybatisDaoFactory daoFactory(SqlSessionFactory sqlSessionFactory) {

    return new MybatisDaoFactory(sqlSessionFactory);
  }

  @Bean
  public PlatformTransactionManager TransactionManager(//

      SqlSessionFactory sqlSessionFactory//
  ) {
    return new PlatformTransactionManager(sqlSessionFactory);
  }

  @Bean
  public BoardDao BoardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(BoardDao.class);
  }

  @Bean
  public PlanDao PlanDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PlanDao.class);
  }

  @Bean
  public MemberDao MemberDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(MemberDao.class);
  }

  @Bean
  public PhotoBoardDao PhotoBoardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoBoardDao.class);
  }

  @Bean
  public PhotoFileDao PhotoFileDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoFileDao.class);
  }
}
