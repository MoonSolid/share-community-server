package com.moonsolid.sc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/plan/addForm")
public class PlanAddFormServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();


      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>일정 입력</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>일정 입력</h1>");
      out.println("<form action='add'>");
      out.println("일정명: <input name='title' type='text'><br>");
      out.println("내용:<br>");
      out.println("<textarea name='description' rows='5' cols='60'></textarea><br>");
      out.println("일정 장소: <input name='place' type='text'><br>");
      out.println("메모: <input name='memo' type='text'><br>");
      out.println("비용: <input name='cost' type='text'><br>");
      out.println("<button>제출</button>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
