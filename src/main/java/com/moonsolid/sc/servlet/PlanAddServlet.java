package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.Prompt;
import com.moonsolid.util.RequestMapping;

@Component
public class PlanAddServlet {

  PlanService planService;

  public PlanAddServlet(PlanService planService) {
    this.planService = planService;
  }

  @RequestMapping("/plan/add")
  public void service(Scanner in, PrintStream out) throws Exception {
    Plan plan = new Plan();

    plan.setPlace(Prompt.getString(in, out, "일정 장소 : "));
    plan.setDescription(Prompt.getString(in, out, "일정 내용 : "));
    plan.setMemo(Prompt.getString(in, out, "일정 메모 : "));
    plan.setCost(Prompt.getString(in, out, "일정 비용 : "));
    plan.setTitle(Prompt.getString(in, out, "일정 명 : "));

    if (planService.add(plan) > 0) {
      out.println("새 일정을 등록했습니다.");
    } else {
      out.println("일정 등록에 실패했습니다.");
    }
  }
}
