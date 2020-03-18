package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;

public class PlanListServlet implements Servlet {

  PlanDao planDao;

  public PlanListServlet(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Plan> plans = planDao.findAll();
    for (Plan plan : plans) {
      out.printf("%d,%s,%s,%s,%s\n", //
          plan.getNo(), //
          plan.getPlace(), //
          plan.getDescription(), //
          plan.getMemo(), //
          plan.getCost()//
      );
    }
  }
}
