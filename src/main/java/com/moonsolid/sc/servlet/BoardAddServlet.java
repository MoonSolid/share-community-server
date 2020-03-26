package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.domain.Board;
import com.moonsolid.sc.service.BoardService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;

public class BoardAddServlet implements Servlet {

  @Component("/board/add")
  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Board board = new Board();
    board.setTitle(Prompt.getString(in, out, "게시글 제목 : "));
    boardService.add(board);
    out.println("새 게시글을 등록했습니다.");
  }
}
