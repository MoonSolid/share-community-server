package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.domain.PhotoFile;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PhotoBoardService;
import com.moonsolid.sc.service.PlanService;
import com.moonsolid.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardService photoBoardService;
  PlanService planService;

  public PhotoBoardAddServlet(//
      PhotoBoardService photoBoardService, //
      PlanService planService) {
    this.photoBoardService = photoBoardService;
    this.planService = planService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "제목 : "));

    int planNo = Prompt.getInt(in, out, "일정번호 : ");

    Plan plan = planService.get(planNo);
    if (plan == null) {
      out.println("일정 번호가 유효하지 않습니다.");
    }

    photoBoard.setPlan(plan);

    List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
    photoBoard.setFiles(photoFiles);

    photoBoardService.add(photoBoard);
    out.println("새 사진 게시글을 등록했습니다.");
  }

  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {
    out.println("최소 한개의 사진파일을 등록해야 합니다.");
    out.println("파일명 입력 없이  엔터를 치면 파일 추가를 마칩니다.");

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();

    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일?");

      if (filepath.length() == 0) {
        if (photoFiles.size() > 0) {
          break;
        } else {
          out.println("최소 한개의 사진파일을 등록해야 합니다.");
          continue;
        }
      }
      photoFiles.add(new PhotoFile().setFilepath(filepath));
    }
    return photoFiles;
  }
}
