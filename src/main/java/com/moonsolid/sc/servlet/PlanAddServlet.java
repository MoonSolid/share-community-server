package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.util.Prompt;

public class PlanAddServlet implements Servlet {

  PlanDao planDao;

  public PlanAddServlet(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Plan plan = new Plan();

    plan.setPlace(Prompt.getString(in, out, "일정 장소 : "));
    plan.setDescription(Prompt.getString(in, out, "일정 내용 : "));
    plan.setMemo(Prompt.getString(in, out, "일정 메모 : "));
    plan.setCost(Prompt.getString(in, out, "일정 비용 : "));

    if (planDao.insert(plan) > 0) {
      out.println("새 일정을 등록했습니다.");
    } else {
      out.println("일정 등록에 실패했습니다.");
    }
  }
}
