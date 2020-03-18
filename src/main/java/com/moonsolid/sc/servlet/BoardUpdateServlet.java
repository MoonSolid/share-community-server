package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.domain.Board;

public class BoardUpdateServlet implements Servlet {

  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("게시글 번호 : ");
    out.println("!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    Board old = boardDao.findByNo(no);
    if (old == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    out.printf("(기존 제목 - %s) 게시글 제목 :\n", old.getTitle());
    out.println("!{}!");
    out.flush();

    Board board = new Board();
    board.setTitle(in.nextLine());
    board.setNo(no);

    if (boardDao.update(board) > 0) {
      out.println("게시글을 변경했습니다.");

    } else {
      out.println("게시글 변경에 실패했습니다.");
    }
  }
}
