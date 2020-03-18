package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;

public class PlanUpdateServlet implements Servlet {

  PlanDao planDao;

  public PlanUpdateServlet(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("일정 번호 : ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    Plan old = planDao.findByNo(no);
    if (old == null) {
      out.println("해당 번호의 일정이 없습니다.");
      return;
    }

    out.printf("(기존 내용 - %s) 일정 내용 :\n", old.getDescription());
    out.println("!{}!");
    out.flush();

    Plan plan = new Plan();
    plan.setDescription(in.nextLine());
    plan.setNo(no);

    if (planDao.update(plan) > 0) {
      out.println("일정을 변경했습니다.");

    } else {
      out.println("일정 변경에 실패했습니다.");
    }
  }

}
