package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.RequestMapping;

@Component
public class PlanAddServlet {

  PlanService planService;

  public PlanAddServlet(PlanService planService) {
    this.planService = planService;
  }

  @RequestMapping("/plan/add")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    Plan plan = new Plan();
    plan.setTitle(params.get("title"));
    plan.setDescription(params.get("description"));
    plan.setPlace(params.get("place"));
    plan.setMemo(params.get("memo"));
    plan.setCost(params.get("cost"));

    planService.add(plan);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/plan/list'>");
    out.println("<title>일정 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>일정 입력 결과</h1>");
    out.println("<p>새 일정을 등록했습니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }
}
