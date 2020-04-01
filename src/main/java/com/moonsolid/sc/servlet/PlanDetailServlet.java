package com.moonsolid.sc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;

@WebServlet("/plan/detail")
public class PlanDetailServlet extends GenericServlet {
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

      int no = Integer.parseInt(req.getParameter("no"));
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
        out.println("<form action='update'>");
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
        out.printf("<a href='delete?no=%d'>삭제</a>\n", //
            plan.getNo());
        out.printf("<a href='list?planNo=%d'>사진게시판</a>\n", //
            plan.getNo());
        out.println("</p>");
        out.println("</form>");
      } else {
        out.println("<p>해당 번호의 일정이 없습니다.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
