package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;

@Component("/plan/update")
public class PlanUpdateServlet implements Servlet {

  PlanService planService;

  public PlanUpdateServlet(PlanService planService) {
    this.planService = planService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "일정 번호 : ");

    Plan old = planService.get(no);
    if (old == null) {
      out.println("해당 번호의 일정이 없습니다.");
      return;
    }

    Plan plan = new Plan();

    plan.setNo(no);
    plan.setPlace(Prompt.getString(in, out, //
        String.format("일정 장소(기존 장소 : (%s))", old.getPlace())));
    plan.setDescription(Prompt.getString(in, out, //
        String.format("일정 내용(기존 내용 : (%s))", old.getDescription())));
    plan.setMemo(Prompt.getString(in, out, //
        String.format("일정 메모(기존 메모 : (%s))", old.getMemo())));
    plan.setCost(Prompt.getString(in, out, //
        String.format("일정 비용(기존 비용 : (%s))", old.getCost())));
    plan.setTitle(Prompt.getString(in, out, //
        String.format("일정 제목(기존 제목 : (%s))", old.getTitle())));

    if (planService.update(plan) > 0) {
      out.println("일정을 변경했습니다.");

    } else {
      out.println("일정 변경에 실패했습니다.");
    }
  }

}
