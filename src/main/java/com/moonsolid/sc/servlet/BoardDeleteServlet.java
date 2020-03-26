package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.service.BoardService;
import com.moonsolid.util.Prompt;
import com.moonsolid.util.RequestMapping;

@Component
public class BoardDeleteServlet {

  BoardService boardService;

  public BoardDeleteServlet(BoardService boardService) {
    this.boardService = boardService;
  }


  @RequestMapping("/board/delete")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "게시글 번호 : ");

    if (boardService.delete(no) > 0) {
      out.println("게시글을 삭제했습니다.");

    } else {
      out.println("해당 번호의 게시글이 없습니다.");
    }
  }


}
