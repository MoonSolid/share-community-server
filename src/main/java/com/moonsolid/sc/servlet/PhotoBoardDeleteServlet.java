package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.dao.PhotoFileDao;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호 : ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    photoFileDao.deleteAll(no);

    if (photoBoardDao.delete(no) > 0) {
      out.println("사진 게시글을 삭제했습니다.");
    } else {
      out.println("사진 게시글이 없습니다");
    }
  }
}

