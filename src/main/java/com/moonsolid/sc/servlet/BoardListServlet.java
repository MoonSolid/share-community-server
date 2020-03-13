package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.domain.Board;

public class BoardListServlet {

  List<Board> boards;

  public BoardListServlet(List<Board> boards) {
    this.boards = boards;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    out.writeUTF("OK");
    out.reset();
    out.writeObject(boards);

  }
}
