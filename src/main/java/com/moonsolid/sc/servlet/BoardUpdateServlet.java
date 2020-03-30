package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.Board;
import com.moonsolid.sc.service.BoardService;
import com.moonsolid.util.RequestMapping;

@Component
public class BoardUpdateServlet {

  BoardService boardService;

  public BoardUpdateServlet(BoardService boardService) {
    this.boardService = boardService;
  }


  @RequestMapping("/board/update")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    Board board = new Board();
    board.setNo(Integer.parseInt(params.get("no")));
    board.setTitle(params.get("title"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/board/list'>");
    out.println("<title>게시글 변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글 변경 결과</h1>");

    if (boardService.update(board) > 0) {
      out.println("<p>게시글을 변경했습니다.</p>");

    } else {
      out.println("<p>해당 번호의 게시글이 없습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
