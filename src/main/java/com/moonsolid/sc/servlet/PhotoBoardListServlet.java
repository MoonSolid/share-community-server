package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PhotoBoardService;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;
import com.moonsolid.util.RequestMapping;

@Component
public class PhotoBoardListServlet implements Servlet {

  PhotoBoardService photoBoardService;
  PlanService planService;

  public PhotoBoardListServlet(//
      PhotoBoardService photoBoardService, //
      PlanService planService) {
    this.photoBoardService = photoBoardService;
    this.planService = planService;
  }


  @RequestMapping("/photoboard/list")
  public void service(Scanner in, PrintStream out) throws Exception {
    int planNo = Prompt.getInt(in, out, "일정번호 : ");

    Plan plan = planService.get(planNo);
    if (plan == null) {
      out.println("일정 번호가 유효하지 않습니다.");
      return;
    }

    out.printf("일정명: %s\n", plan.getTitle());
    out.println("----------------------------------------------------------");

    List<PhotoBoard> photoBoards = photoBoardService.listPlanPhoto(planNo);

    for (PhotoBoard photoBoard : photoBoards) {
      out.printf("%d, %s, %s, %d\n", //
          photoBoard.getNo(), //
          photoBoard.getTitle(), //
          photoBoard.getCreatedDate(), //
          photoBoard.getViewCount() //
      );
    }
  }

}
