package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.RequestMapping;

@Component
public class PlanSearchServlet {

  PlanService planService;

  public PlanSearchServlet(PlanService planService) {
    this.planService = planService;
  }

  @RequestMapping("/plan/search")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    HashMap<String, Object> map = new HashMap<>();
    String value;

    value = params.get("title");
    if (value.length() > 0) {
      map.put("title", value);
    }

    value = params.get("description");
    if (value.length() > 0) {
      map.put("description", value);
    }

    value = params.get("place");
    if (value.length() > 0) {
      map.put("place", value);
    }

    value = params.get("memo");
    if (value.length() > 0) {
      map.put("memo", value);
    }

    value = params.get("cost");
    if (value.length() > 0) {
      map.put("cost", value);
    }

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>일정 검색</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("  <h1>일정 검색 결과</h1>");
    out.println("  <table border='1'>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>일정명</th>");
    out.println("    <th>장소</th>");
    out.println("    <th>메모</th>");
    out.println("    <th>비용</th>");
    out.println("    <th>일정내용</th>");
    out.println("  </tr>");

    List<Plan> plans = planService.search(map);
    for (Plan p : plans) {
      out.printf("  <tr>"//
          + "<td>%d</td>"//
          + "<td><a href='/plan/detail?no=%d'>%s</a></td>"//
          + "<td>%s</td>"//
          + "<td>%s</td>"//
          + "<td>%s</td>"//
          + "<td>%s</td>"//
          + "</tr>\n", //
          p.getNo(), //
          p.getNo(), //
          p.getTitle(), //
          p.getPlace(), //
          p.getMemo(), //
          p.getCost(), //
          p.getDescription() //
      );
    }
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }
}
