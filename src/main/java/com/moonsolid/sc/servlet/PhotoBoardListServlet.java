package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.util.Prompt;

public class PhotoBoardListServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PlanDao planDao;

  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao, PlanDao planDao) {
    this.photoBoardDao = photoBoardDao;
    this.planDao = planDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int planNo = Prompt.getInt(in, out, "일정번호 : ");

    Plan plan = planDao.findByNo(planNo);

    if (plan == null) {
      out.println("일정 번호가 유효하지 않습니다.");
      return;
    }

    out.printf("일정명: %s\n", plan.getTitle());
    out.println("----------------------------------------------------------");

    List<PhotoBoard> photoBoards = photoBoardDao.findAllByPlanNo(planNo);

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
