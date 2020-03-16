package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;

public class PlanDetailServlet implements Servlet {

  PlanDao planDao;

  public PlanDetailServlet(PlanDao planDao) {
    this.planDao = planDao;
  }



  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Plan plan = planDao.findByNo(no);

    if (plan != null) {
      out.writeUTF("OK");
      out.writeObject(plan);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 일정이 없습니다.");
    }
  }
}
