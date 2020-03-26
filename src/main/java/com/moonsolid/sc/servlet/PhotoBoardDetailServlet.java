package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.domain.PhotoFile;
import com.moonsolid.sc.service.PhotoBoardService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;

@Component("/photoboard/detail")
public class PhotoBoardDetailServlet implements Servlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardDetailServlet( //
      PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "사진 게시글번호? ");

    PhotoBoard photoBoard = photoBoardService.get(no);

    if (photoBoard != null) {
      out.printf("번호: %d\n", photoBoard.getNo());
      out.printf("제목: %s\n", photoBoard.getTitle());
      out.printf("등록일: %s\n", photoBoard.getCreatedDate());
      out.printf("조회수: %d\n", photoBoard.getViewCount());
      out.printf("장소: %s\n", photoBoard.getPlan().getPlace());
      out.println("사진 파일:");

      for (PhotoFile photoFile : photoBoard.getFiles()) {
        out.printf("> %s\n", photoFile.getFilepath());
      }
    } else {
      out.println("해당 번호의 사진 게시글이 없습니다.");
    }
  }
}
