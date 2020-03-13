package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.domain.Board;

public class BoardAddServlet implements Servlet {

  List<Board> boards;

  public BoardAddServlet(List<Board> boards) {
    this.boards = boards;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Board board = (Board) in.readObject();

    int i = 0;
    for (; i < boards.size(); i++) {
      if (boards.get(i).getNo() == board.getNo()) {
        break;
      }
    }

    if (i == boards.size()) {
      boards.add(board);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시글이 있습니다.");
    }
  }
}