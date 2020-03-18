package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.domain.Board;

public class BoardListServlet implements Servlet {

  BoardDao boardDao;

  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Board> boards = boardDao.findAll();
    for (Board board : boards) {
      out.printf("%d, %s, %s, %d\n", //
          board.getNo(), //
          board.getTitle(), //
          board.getDate(), //
          board.getViewCount()//
      );
    }
  }
}
