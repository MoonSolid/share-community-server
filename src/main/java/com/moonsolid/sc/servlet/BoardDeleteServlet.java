package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.dao.BoardDao;

public class BoardDeleteServlet implements Servlet {

  BoardDao boardDao;

  public BoardDeleteServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("게시글 번호 : ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    if (boardDao.delete(no) > 0) {
      out.println("게시글을 삭제했습니다.");
    } else {
      out.println("게시글이 없습니다");
    }
  }


}
