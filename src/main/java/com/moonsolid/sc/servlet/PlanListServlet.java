package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;

public class PlanListServlet implements Servlet {

  PlanService planService;

  public PlanListServlet(PlanService planService) {
    this.planService = planService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Plan> plans = planService.list();
    for (Plan plan : plans) {
      out.printf("%d,%s,%s,%s,%s,%s\n", //
          plan.getNo(), //
          plan.getPlace(), //
          plan.getDescription(), //
          plan.getMemo(), //
          plan.getCost(), //
          plan.getTitle() //
      );
    }
  }
}
