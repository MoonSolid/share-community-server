package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.RequestMapping;

@Component
public class PhotoBoardAddFormServlet {

  PlanService planService;

  public PhotoBoardAddFormServlet(PlanService planService) {
    this.planService = planService;
  }

  @RequestMapping("/photoboard/addForm")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    int planNo = Integer.parseInt(params.get("planNo"));
    Plan plan = planService.get(planNo);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>사진 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>사진 입력</h1>");
    out.println("<form action='/photoboard/add'>");
    out.printf("일정번호: <input name='planNo' type='text' value='%d' readonly><br>\n", //
        plan.getNo());
    out.printf("일정명: %s<br>\n", plan.getTitle());
    out.println("내용:<br>");
    out.println("<textarea name='title' rows='5' cols='60'></textarea><br>");
    out.println("<hr>");
    out.println("사진: <input name='photo1' type='file'><br>");
    out.println("사진: <input name='photo2' type='file'><br>");
    out.println("사진: <input name='photo3' type='file'><br>");
    out.println("사진: <input name='photo4' type='file'><br>");
    out.println("사진: <input name='photo5' type='file'><br>");
    out.println("<button>제출</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");

  }
}
