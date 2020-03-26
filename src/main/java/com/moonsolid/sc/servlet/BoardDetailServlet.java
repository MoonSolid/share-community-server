package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.domain.Board;
import com.moonsolid.sc.service.BoardService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;

@Component("/board/detail")
public class BoardDetailServlet implements Servlet {

  BoardService boardService;

  public BoardDetailServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "게시글 번호 : ");

    Board board = boardService.get(no);

    if (board != null) {
      out.printf("번호: %d\n", board.getNo());
      out.printf("제목: %s\n", board.getTitle());
      out.printf("등록일: %s\n", board.getDate());
      out.printf("조회수: %d\n", board.getViewCount());
    } else {
      out.println("해당 번호의 게시글이 없습니다.");
    }
  }
}
