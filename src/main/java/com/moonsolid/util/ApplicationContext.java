package com.moonsolid.util;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.io.Resources;

public class ApplicationContext {
  ArrayList<String> classNames = new ArrayList<>();

  ArrayList<Class<?>> ComponentClasses = new ArrayList<>();

  HashMap<String, Object> objPool = new HashMap<>();

  public ApplicationContext(String packageName, Map<String, Object> beans) throws Exception {
    Set<String> keySet = beans.keySet();
    for (String key : keySet) {
      objPool.put(key, beans.get(key));
    }

    File path = Resources.getResourceAsFile(//
        packageName.replace('.', '/'));

    findClasses(path, packageName);

    prepareComponentClasses();

    for (Class<?> clazz : ComponentClasses) {
      try {
        createInstance(clazz);
      } catch (Exception e) {
        System.out.printf("%s 클래스의 객체를 생성할 수 없습니다.\n", //
            clazz.getName());
      }
    }
  }

  public void printBeans() {
    System.out.println("---------------------------------");
    Set<String> beanNames = objPool.keySet();
    for (String beanName : beanNames) {
      System.out.printf("%s =====> %s\n", //
          beanName, objPool.get(beanName).getClass().getName());
    }
  }


  public void addBean(String name, Object bean) {
    objPool.put(name, bean);
  }

  public Object getBean(String name) {
    return objPool.get(name);
  }

  private void prepareComponentClasses() throws Exception {
    for (String className : classNames) {

      Class<?> clazz = Class.forName(className);
      if (!isComponentClass(clazz)) {
        continue;
      }
      ComponentClasses.add(clazz);
    }
  }

  private Object createInstance(Class<?> clazz) throws Exception {

    Constructor<?> constructor = clazz.getConstructors()[0];

    Parameter[] params = constructor.getParameters();

    ArrayList<Object> values = new ArrayList<>();
    for (Parameter param : params) {
      values.add(getParameterInstance(param));
    }

    Object obj = constructor.newInstance(values.toArray());

    objPool.put(getBeanName(clazz), obj);
    return obj;
  }

  private String getBeanName(Class<?> clazz) {
    Component compAnno = clazz.getAnnotation(Component.class);
    if (compAnno == null || compAnno.value().length() == 0) {
      return clazz.getName();
    }
    return compAnno.value();
  }

  private Object getParameterInstance(Parameter param) throws Exception {
    Collection<?> objs = objPool.values();

    for (Object obj : objs) {
      if (param.getType().isInstance(obj)) {
        return obj;
      }
    }

    Class<?> clazz = findParameterClassInfo(param.getType());
    if (clazz == null) {
      return null;
    }
    return createInstance(clazz);
  }

  private Class<?> findParameterClassInfo(Class<?> paramType) throws Exception {
    for (Class<?> clazz : ComponentClasses) {
      if (paramType.isInterface()) {
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> interfaceInfo : interfaces) {
          if (interfaceInfo == paramType) {
            return clazz;
          }
        }
      } else if (isType(clazz, paramType)) {
        return clazz;
      }
    }

    return null;
  }

  private boolean isType(Class<?> clazz, Class<?> target) {

    if (clazz == target) {
      return true;
    }

    if (clazz == Object.class) {
      return false;
    }

    return isType(clazz.getSuperclass(), target);
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


  private void findClasses(File path, String packageName) {

    File[] files = path.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        if (file.isDirectory() //
            || (file.getName().endsWith(".class")//
                && !file.getName().contains("$")))
          return true;
        return false;
      }
    });
    for (File f : files) {
      String classOrPackageName = //
          packageName + "." + f.getName().replace(".class", "");
      if (f.isFile()) {
        classNames.add(classOrPackageName);
      } else {
        findClasses(f, classOrPackageName);
      }
    }

  }
}
