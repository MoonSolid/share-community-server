package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;
import com.moonsolid.util.RequestMapping;

@Component
public class PlanDetailServlet {

  PlanService planService;

  public PlanDetailServlet(PlanService planService) {
    this.planService = planService;
  }



  @RequestMapping("/plan/detail")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "일정 번호 : ");

    Plan plan = planService.get(no);

    if (plan != null) {
      out.printf("일정번호: %d\n", plan.getNo());
      out.printf("일정장소: %s\n", plan.getPlace());
      out.printf("일정내용: %s\n", plan.getDescription());
      out.printf("일정메모: %s\n", plan.getMemo());
      out.printf("일정비용: %s\n", plan.getCost());
      out.printf("일정명 : %s\n", plan.getTitle());
    } else {
      out.println("해당 번호의 일정 없습니다.");
    }
  }
}
