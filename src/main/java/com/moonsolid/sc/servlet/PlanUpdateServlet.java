package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.dao.PlanObjectFileDao;
import com.moonsolid.sc.domain.Plan;

public class PlanUpdateServlet implements Servlet {

  PlanObjectFileDao planDao;

  public PlanUpdateServlet(PlanObjectFileDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Plan plan = (Plan) in.readObject();

    if (planDao.update(plan) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 일정이 없습니다.");
    }
  }

}
