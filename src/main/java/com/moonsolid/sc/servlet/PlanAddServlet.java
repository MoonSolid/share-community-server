package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;

public class PlanAddServlet implements Servlet {

  PlanDao planDao;

  public PlanAddServlet(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Plan plan = new Plan();

    out.println("일정 장소 : \n!{}!");
    plan.setPlace(in.nextLine());
    out.println("일정 내용 : \n!{}!");
    plan.setDescription(in.nextLine());
    out.println("일정 메모 : \n!{}!");
    plan.setMemo(in.nextLine());
    out.println("일정 비용 : \n!{}!");
    plan.setCost(in.nextLine());

    if (planDao.insert(plan) > 0) {
      out.println("새 일정을 등록했습니다.");
    } else {
      out.println("일정 등록에 실패했습니다.");
    }
  }
}
