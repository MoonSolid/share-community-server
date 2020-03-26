package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;
import com.moonsolid.util.RequestMapping;

@Component
public class PlanDeleteServlet {

  PlanService planService;

  public PlanDeleteServlet(PlanService planService) {
    this.planService = planService;
  }



  @RequestMapping("/plan/delete")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "일정 번호 : ");

    if (planService.delete(no) > 0) {
      out.println("일정을 삭제했습니다.");
    } else {
      out.println("일정이 없습니다");
    }
  }
}
