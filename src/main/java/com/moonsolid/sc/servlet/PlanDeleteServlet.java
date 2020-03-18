package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.dao.PlanDao;

public class PlanDeleteServlet implements Servlet {

  PlanDao planDao;

  public PlanDeleteServlet(PlanDao planDao) {
    this.planDao = planDao;
  }



  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("일정 번호 : ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    if (planDao.delete(no) > 0) {
      out.println("일정을 삭제했습니다.");
    } else {
      out.println("일정이 없습니다");
    }
  }
}
