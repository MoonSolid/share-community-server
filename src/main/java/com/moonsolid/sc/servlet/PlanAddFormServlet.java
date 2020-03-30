package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.moonsolid.util.RequestMapping;

@Component
public class PlanAddFormServlet {
  @RequestMapping("/plan/addForm")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>일정 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>일정 입력</h1>");
    out.println("<form action='/plan/add'>");
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
  }
}
