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

@WebServlet("/plan/update")
public class PlanUpdateServlet extends GenericServlet {
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

      Plan plan = new Plan();
      plan.setNo(Integer.parseInt(req.getParameter("no")));
      plan.setPlace(req.getParameter("place"));
      plan.setDescription(req.getParameter("description"));
      plan.setMemo(req.getParameter("memo"));
      plan.setCost(req.getParameter("cost"));
      plan.setTitle(req.getParameter("title"));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
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
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
