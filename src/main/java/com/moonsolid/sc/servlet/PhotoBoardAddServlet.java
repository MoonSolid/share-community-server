package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.dao.PhotoFileDao;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.domain.PhotoFile;
import com.moonsolid.sc.domain.Plan;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PlanDao planDao;
  PhotoFileDao photoFileDao;


  public PhotoBoardAddServlet(//
      PhotoBoardDao photoBoardDao, //
      PlanDao planDao, //
      PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.planDao = planDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();

    out.println("제목 : ");
    out.println("!{}!");
    out.flush();
    photoBoard.setTitle(in.nextLine());

    out.println("일정번호 : ");
    out.println("!{}!");
    out.flush();

    int planNo = Integer.parseInt(in.nextLine());

    Plan plan = planDao.findByNo(planNo);
    if (plan == null) {
      out.println("일정 번호가 유효하지 않습니다.");
    }

    photoBoard.setPlan(plan);

    if (photoBoardDao.insert(photoBoard) > 0) {
      out.println("최소 한개의 사진파일을 등록해야 합니다.");
      out.println("파일명 입력 없이 엔터를 치면 파일 추가를 마칩니다.");

      ArrayList<PhotoFile> photoFiles = new ArrayList<>();

      while (true) {
        out.println("사진파일 : ");
        out.println("!{}!");
        out.flush();
        String filepath = in.nextLine();

        if (filepath.length() == 0) {
          if (photoFiles.size() > 0) {
            break;
          } else {
            out.println("최소 한개의 사진파일을 등록해야 합니다.");
            continue;
          }
        }
        photoFiles.add(new PhotoFile().setFilepath(filepath).setBoardNo(photoBoard.getNo()));

      }

      for (PhotoFile photoFile : photoFiles) {
        photoFileDao.insert(photoFile);
      }
      out.println("새 사진 게시글을 등록했습니다.");

    } else {
      out.println("사진 게시글 등록에 실패했습니다.");
    }
  }
}
