package com.moonsolid.sc;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@ComponentScan(value = "com.moonsolid.sc")

@PropertySource("classpath:com/moonsolid/sc/conf/jdbc.properties")

@MapperScan("com.moonsolid.sc.dao")

public class AppConfig {

  @Value("${jdbc.driver}")
  String jdbcDriver;

  @Value("${jdbc.url}")
  String jdbcUrl;

  @Value("${jdbc.username}")
  String jdbcUsername;

  @Value("${jdbc.password}")
  String jdbcPassword;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx)
      throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("com.moonsolid.sc.domain");
    sqlSessionFactoryBean.setMapperLocations(//
        appCtx.getResources("classpath:com/moonsolid/sc/mapper/*Mapper.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public PlatformTransactionManager TransactionManager(//
      DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
