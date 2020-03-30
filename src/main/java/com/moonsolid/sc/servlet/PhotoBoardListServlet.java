package com.moonsolid.sc.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PhotoBoardService;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.RequestMapping;

@Component
public class PhotoBoardListServlet {

  PhotoBoardService photoBoardService;
  PlanService planService;

  public PhotoBoardListServlet(//
      PhotoBoardService photoBoardService, //
      PlanService planService) {
    this.photoBoardService = photoBoardService;
    this.planService = planService;
  }


  @RequestMapping("/photoboard/list")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>일정 사진 목록</title>");
    out.println("</head>");
    out.println("<body>");
    try {
      int planNo = Integer.parseInt(params.get("planNo"));
      Plan plan = planService.get(planNo);
      if (plan == null) {
        throw new Exception("일정 번호가 유효하지 않습니다.");
      }

      out.printf("  <h1>일정 사진 - %s</h1>", plan.getTitle());
      out.printf("  <a href='/photoboard/addForm?planNo=%d'>새 사진</a><br>\n", //
          planNo);
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>제목</th>");
      out.println("    <th>등록일</th>");
      out.println("    <th>조회수</th>");
      out.println("  </tr>");

      List<PhotoBoard> photoBoards = photoBoardService.listPlanPhoto(planNo);
      for (PhotoBoard photoBoard : photoBoards) {
        out.printf("  <tr>"//
            + "<td>%d</td> "//
            + "<td><a href='/photoboard/detail?no=%d'>%s</a></td> "//
            + "<td>%s</td> "//
            + "<td>%d</td>"//
            + "</tr>\n", //
            photoBoard.getNo(), //
            photoBoard.getNo(), //
            photoBoard.getTitle(), //
            photoBoard.getCreatedDate(), //
            photoBoard.getViewCount() //
        );
      }
      out.println("</table>");

    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println("</body>");
    out.println("</html>");
  }
}
