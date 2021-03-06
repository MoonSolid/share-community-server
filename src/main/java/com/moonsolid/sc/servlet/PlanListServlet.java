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
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;

@WebServlet("/plan/list")
public class PlanListServlet extends GenericServlet {
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
      PlanService planService = iocContainer.getBean(PlanService.class);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("  <meta charset='UTF-8'>");
      out.println("  <title>일정 목록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("  <h1>일정</h1>");
      out.println("  <a href='addForm'>새 일정</a><br>");
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>일정명</th>");
      out.println("    <th>장소</th>");
      out.println("    <th>메모</th>");
      out.println("    <th>비용</th>");
      out.println("    <th>일정내용</th>");
      out.println("  </tr>");

      List<Plan> plans = planService.list();
      for (Plan p : plans) {
        out.printf("  <tr>"//
            + "<td>%d</td> "//
            + "<td><a href='detail?no=%d'>%s</a></td> "//
            + "<td>%s</td> "//
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

      out.println("<hr>");

      out.println("<form action='search'>");
      out.println("일정명: <input name='title' type='text'><br>");
      out.println("일정내용: <input name='description' type='text'><br>");
      out.println("장소: <input name='place' type='text'><br>");
      out.println("메모: <input name='memo' type='text'><br>");
      out.println("비용: <input name='cost' type='text'><br>");
      out.println("<button>검색</button>");

      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
