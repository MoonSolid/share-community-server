package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.domain.Board;

public class BoardAddServlet implements Servlet {

  BoardDao boardDao;

  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Board board = new Board();

    out.println("게시글 제목 : \n!{}!");
    board.setTitle(in.nextLine());

    if (boardDao.insert(board) > 0) {
      out.println("새 게시글을 등록했습니다.");

    } else {
      out.println("게시글 등록에 실패했습니다.");
    }
  }
}
