package com.moonsolid.sc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PhotoBoardService;
import com.moonsolid.sc.service.PlanService;

@WebServlet("/photoboard/list")
public class PhotoBoardListServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();

      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);
      PlanService planService = iocContainer.getBean(PlanService.class);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("  <meta charset='UTF-8'>");
      out.println("  <title>일정 사진 목록</title>");
      out.println("</head>");
      out.println("<body>");
      try {
        int planNo = Integer.parseInt(req.getParameter("planNo"));
        Plan plan = planService.get(planNo);
        if (plan == null) {
          throw new Exception("일정 번호가 유효하지 않습니다.");
        }

        out.printf("  <h1>일정 사진 - %s</h1>", plan.getTitle());
        out.printf("  <a href='addForm?planNo=%d'>새 사진</a><br>\n", //
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
              + "<td><a href='detail?no=%d'>%s</a></td> "//
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
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
