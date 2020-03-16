package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.domain.Plan;

public class PlanUpdateServlet {

  List<Plan> plans;

  public PlanUpdateServlet(List<Plan> plans) {
    this.plans = plans;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Plan plan = (Plan) in.readObject();

    int index = -1;
    for (int i = 0; i < plans.size(); i++) {
      if (plans.get(i).getNo() == plan.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      plans.set(index, plan);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 일정이 없습니다.");
    }
  }



}
