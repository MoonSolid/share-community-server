package com.moonsolid.sc;

import java.io.InputStream;
import java.lang.reflect.Method;
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
import com.moonsolid.util.Component;
import com.moonsolid.util.RequestHandler;
import com.moonsolid.util.RequestMapping;
import com.moonsolid.util.RequestMappingHandlerMapping;

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



      System.out.println("----------------------------");

      // @Component 애노테이션이 붙은 객체를 찾는다.
      RequestMappingHandlerMapping handlerMapper = //
          new RequestMappingHandlerMapping();
      String[] beanNames = appCtx.getBeanNamesForAnnotation(Component.class);
      for (String beanName : beanNames) {
        Object component = appCtx.getBean(beanName);

        // @RequestHandler가 붙은 메서드를 찾는다.
        Method method = getRequestHandler(component.getClass());
        if (method != null) {
          // 클라이언트 명령을 처리하는 메서드 정보를 준비한다.
          RequestHandler requestHandler = new RequestHandler(method, component);

          // 명령을 처리할 메서드를 찾을 수 있도록
          // 명령 이름으로 메서드 정보를 저장한다.
          handlerMapper.addHandler(requestHandler.getPath(), requestHandler);
        }
      }

      // ServerApp 에서 request handler를 사용할 수 있도록 공유한다.
      context.put("handlerMapper", handlerMapper);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Method getRequestHandler(Class<?> type) {
    // 클라이언트 명령을 처리할 메서드는 public 이기 때문에
    // 클래스에서 public 메서드만 조사한다.
    Method[] methods = type.getMethods();
    for (Method m : methods) {
      // 메서드에 @RequestMapping 애노테이션이 붙었는지 검사한다.
      RequestMapping anno = m.getAnnotation(RequestMapping.class);
      if (anno != null) {
        return m;
      }
    }

    return null;
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

  }

}
