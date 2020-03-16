package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.dao.PlanObjectFileDao;

public class PlanListServlet implements Servlet {

  PlanObjectFileDao planDao;

  public PlanListServlet(PlanObjectFileDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(planDao.findAll());
  }


}
