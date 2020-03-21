package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.Scanner;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.dao.PhotoFileDao;
import com.moonsolid.util.ConnectionFactory;
import com.moonsolid.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

  ConnectionFactory conFactory;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(//
      ConnectionFactory conFactory, //
      PhotoBoardDao photoBoardDao, //
      PhotoFileDao photoFileDao) {
    this.conFactory = conFactory;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    Connection con = conFactory.getConnection();

    con.setAutoCommit(false);

    try {
      photoFileDao.deleteAll(no);
      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("사진 게시글이 없습니다");
      }
      con.commit();
      out.println("사진 게시글을 삭제했습니다.");
    } catch (Exception e) {
      con.rollback();
      out.println(e.getMessage());
    } finally {
      con.setAutoCommit(true);
    }
  }
}

