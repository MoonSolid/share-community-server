package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.domain.Plan;

public class PlanDeleteServlet {

  List<Plan> plans;

  public PlanDeleteServlet(List<Plan> plans) {
    this.plans = plans;
  }


  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = -1;
    for (int i = 0; i < plans.size(); i++) {
      if (plans.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index != -1) {
      plans.remove(index);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 일정이 없습니다.");
    }
  }
}
