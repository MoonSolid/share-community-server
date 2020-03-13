package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.domain.Plan;

public class PlanAddServlet {

  List<Plan> plans;

  public PlanAddServlet(List<Plan> plans) {
    this.plans = plans;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Plan plan = (Plan) in.readObject();

    int i = 0;
    for (; i < plans.size(); i++) {
      if (plans.get(i).getNo() == plan.getNo()) {
        break;
      }
    }

    if (i == plans.size()) {
      plans.add(plan);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 일정이 있습니다.");
    }

  }


}
