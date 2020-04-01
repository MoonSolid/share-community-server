package com.moonsolid.sc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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

@WebServlet("/plan/search")
public class PlanSearchServlet extends GenericServlet {
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

      HashMap<String, Object> map = new HashMap<>();
      String value;

      value = req.getParameter("title");
      if (value.length() > 0) {
        map.put("title", value);
      }

      value = req.getParameter("description");
      if (value.length() > 0) {
        map.put("description", value);
      }

      value = req.getParameter("place");
      if (value.length() > 0) {
        map.put("place", value);
      }

      value = req.getParameter("memo");
      if (value.length() > 0) {
        map.put("memo", value);
      }

      value = req.getParameter("cost");
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
            + "<td><a href='detail?no=%d'>%s</a></td>"//
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
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
