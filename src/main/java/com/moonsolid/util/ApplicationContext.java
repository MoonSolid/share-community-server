package com.moonsolid.util;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import org.apache.ibatis.io.Resources;

public class ApplicationContext {

  ArrayList<Class<?>> concreteClasses = new ArrayList<>();

  HashMap<String, Object> objPool = new HashMap<>();

  public ApplicationContext(String packageName, HashMap<String, Object> beans) throws Exception {
    Set<String> keySet = beans.keySet();
    for (String key : keySet) {
      objPool.put(key, beans.get(key));
    }

    File path = Resources.getResourceAsFile(packageName.replace('.', '/'));

    findClasses(path, packageName);

    for (Class<?> clazz : concreteClasses) {
      try {
        createObject(clazz);
      } catch (Exception e) {
        System.out.printf("%s 클래스의 객체를 생성할 수 없습니다.\n", //
            clazz.getName());
      }
    }
  }

  public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {

    ArrayList<String> beanNames = new ArrayList<>();

    Set<String> beanNameSet = objPool.keySet();
    for (String beanName : beanNameSet) {
      Object obj = objPool.get(beanName);

      if (obj.getClass().getAnnotation(annotationType) != null) {
        beanNames.add(beanName);
      }
    }

    String[] names = new String[beanNames.size()];
    beanNames.toArray(names);
    return names;
  }

  public void printBeans() {
    System.out.println("-----------------------------------");
    Set<String> beanNames = objPool.keySet();
    for (String beanName : beanNames) {
      System.out.printf("%s =====> %s\n", //
          beanName, objPool.get(beanName).getClass().getName());
    }
  }

  public Object getBean(String name) {
    return objPool.get(name);
  }

  private Object createObject(Class<?> clazz) throws Exception {
    Constructor<?> constructor = clazz.getConstructors()[0];
    Parameter[] params = constructor.getParameters();

    System.out.printf("%s()\n", clazz.getName());
    Object[] paramValues = getParameterValues(params);

    Object obj = constructor.newInstance(paramValues);

    objPool.put(getBeanName(clazz), obj);
    System.out.println(clazz.getName() + " 객체 생성!");

    return obj;
  }

  private String getBeanName(Class<?> clazz) {
    Component compAnno = clazz.getAnnotation(Component.class);
    if (compAnno == null || compAnno.value().length() == 0) {
      return clazz.getName();
    }
    return compAnno.value();
  }

  private Object[] getParameterValues(Parameter[] params) throws Exception {
    Object[] values = new Object[params.length];
    System.out.println("파라미터 값: {");
    for (int i = 0; i < values.length; i++) {
      values[i] = getParameterValue(params[i].getType());
      System.out.printf("%s ==> %s,\n", //
          params[i].getType().getSimpleName(), //
          values[i].getClass().getName());
    }
    System.out.println("}");
    return values;
  }

  private Object getParameterValue(Class<?> type) throws Exception {
    Collection<?> objs = objPool.values();
    for (Object obj : objs) {
      if (type.isInstance(obj)) {
        return obj;
      }
    }

    Class<?> availableClass = findAvailableClass(type);
    if (availableClass == null) {
      return null;
    }

    return createObject(availableClass);
  }

  private Class<?> findAvailableClass(Class<?> type) throws Exception {
    for (Class<?> clazz : concreteClasses) {
      if (type.isInterface()) {
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> interfaceInfo : interfaces) {
          if (interfaceInfo == type) {
            return clazz;
          }
        }
      } else if (isChildClass(clazz, type)) {
        return clazz;
      }
    }
    return null;
  }

  private boolean isChildClass(Class<?> clazz, Class<?> type) {
    if (clazz == type) {
      return true;
    }

    if (clazz == Object.class) {
      return false;
    }

    return isChildClass(clazz.getSuperclass(), type);
  }

  private void findClasses(File path, String packageName) throws Exception {
    File[] files = path.listFiles(file -> {
      if (file.isDirectory() //
          || (file.getName().endsWith(".class")//
              && !file.getName().contains("$")))
        return true;
      return false;
    });
    for (File f : files) {
      String className = String.format("%s.%s", //
          packageName, //
          f.getName().replace(".class", ""));
      if (f.isFile()) {
        Class<?> clazz = Class.forName(className);
        if (isComponentClass(clazz)) {
          concreteClasses.add(clazz);
        }
      } else {
        findClasses(f, className);
      }
    }
  }

  private boolean isComponentClass(Class<?> clazz) {
    if (clazz.isInterface() || clazz.isEnum() || Modifier.isAbstract(clazz.getModifiers())) {
      return false;
    }

    Component compAnno = clazz.getAnnotation(Component.class);
    if (compAnno == null) {
      return false;
    }

    return true;
  }
}
