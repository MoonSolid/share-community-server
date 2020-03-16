package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.domain.Plan;

public class PlanListServlet {

  List<Plan> plans;

  public PlanListServlet(List<Plan> plans) {
    this.plans = plans;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    out.writeUTF("OK");
    out.reset();
    out.writeObject(plans);

  }

}
