package com.moonsolid.sc.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.RequestMapping;

@Component
public class PlanDetailServlet {

  PlanService planService;

  public PlanDetailServlet(PlanService planService) {
    this.planService = planService;
  }



  @RequestMapping("/plan/detail")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    int no = Integer.parseInt(params.get("no"));
    Plan plan = planService.get(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>일정 상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>일정 상세정보</h1>");

    if (plan != null) {
      out.println("<form action='/plan/update'>");
      out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n", //
          plan.getNo());
      out.printf("일정명: <input name='title' type='text' value='%s'><br>\n", //
          plan.getTitle());
      out.println("내용:<br>");
      out.printf("<textarea name='description' rows='5' cols='60'>%s</textarea><br>\n", //
          plan.getDescription());
      out.printf("메모: <input name='memo' type='text' value='%s'><br>\n", //
          plan.getMemo());
      out.printf("비용: <input name='cost' type='text' value='%s'><br>\n", //
          plan.getCost());
      out.printf("일정 장소: <input name='place' type='text' value='%s'><br>\n", //
          plan.getPlace());
      out.println("<p>");
      out.println("<button>변경</button>");
      out.printf("<a href='/plan/delete?no=%d'>삭제</a>\n", //
          plan.getNo());
      out.printf("<a href='/photoboard/list?planNo=%d'>사진게시판</a>\n", //
          plan.getNo());
      out.println("</p>");
      out.println("</form>");
    } else {
      out.println("<p>해당 번호의 일정이 없습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }
}
