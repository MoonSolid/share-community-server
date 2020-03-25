package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.domain.Board;
import com.moonsolid.sc.service.BoardService;

public class BoardListServlet implements Servlet {

  BoardService boardService;

  public BoardListServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Board> boards = boardService.list();
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
