package com.moonsolid.sc.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.RequestMapping;

@Component
public class PlanDeleteServlet {

  PlanService planService;

  public PlanDeleteServlet(PlanService planService) {
    this.planService = planService;
  }



  @RequestMapping("/plan/delete")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/plan/list'>");
    out.println("<title>일정 삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>일정 삭제 결과</h1>");

    int no = Integer.parseInt(params.get("no"));
    if (planService.delete(no) > 0) {
      out.println("<p>일정을 삭제했습니다.</p>");

    } else {
      out.println("<p>해당 번호의 일정이 없습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
