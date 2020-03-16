package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;

public class PlanAddServlet implements Servlet {

  PlanDao planDao;

  public PlanAddServlet(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Plan plan = (Plan) in.readObject();

    if (planDao.insert(plan) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 일정이 있습니다.");
    }
  }


}
