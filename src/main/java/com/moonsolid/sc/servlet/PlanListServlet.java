package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.dao.PlanDao;

public class PlanListServlet implements Servlet {

  PlanDao planDao;

  public PlanListServlet(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(planDao.findAll());
  }


}
