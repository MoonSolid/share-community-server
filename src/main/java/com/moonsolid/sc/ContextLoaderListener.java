package com.moonsolid.sc;

import java.lang.reflect.Method;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.context.ApplicationContextListener;
import com.moonsolid.util.RequestHandler;
import com.moonsolid.util.RequestMapping;
import com.moonsolid.util.RequestMappingHandlerMapping;

public class ContextLoaderListener implements ApplicationContextListener {

  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {

      ApplicationContext appCtx = new AnnotationConfigApplicationContext(//
          AppConfig.class);
      printBeans(appCtx);
      context.put("iocContainer", appCtx);

      logger.debug("----------------------------");

      RequestMappingHandlerMapping handlerMapper = //
          new RequestMappingHandlerMapping();
      String[] beanNames = appCtx.getBeanNamesForAnnotation(Component.class);
      for (String beanName : beanNames) {
        Object component = appCtx.getBean(beanName);

        Method method = getRequestHandler(component.getClass());
        if (method != null) {
          RequestHandler requestHandler = new RequestHandler(method, component);

          handlerMapper.addHandler(requestHandler.getPath(), requestHandler);
        }
      }

      // ServerApp 에서 request handler를 사용할 수 있도록 공유한다.
      context.put("handlerMapper", handlerMapper);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void printBeans(ApplicationContext appCtx) {
    logger.debug("Spring IoC 컨테이너에 들어있는 객체들:");
    String[] beanNames = appCtx.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      logger.debug(String.format("%s =======> %s", beanName, //
          appCtx.getBean(beanName).getClass().getName()));
    }
  }


  private Method getRequestHandler(Class<?> type) {
    Method[] methods = type.getMethods();
    for (Method m : methods) {
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
