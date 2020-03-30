package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.RequestMapping;

@Component
public class PlanUpdateServlet {

  PlanService planService;

  public PlanUpdateServlet(PlanService planService) {
    this.planService = planService;
  }

  @RequestMapping("/plan/update")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    Plan plan = new Plan();
    plan.setNo(Integer.parseInt(params.get("no")));
    plan.setPlace(params.get("place"));
    plan.setDescription(params.get("description"));
    plan.setMemo(params.get("memo"));
    plan.setCost(params.get("cost"));
    plan.setTitle(params.get("title"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/plan/list'>");
    out.println("<title>일정 변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>일정 변경 결과</h1>");

    if (planService.update(plan) > 0) {
      out.println("<p>일정을 변경했습니다.</p>");

    } else {
      out.println("<p>일정 변경에 실패했습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}
