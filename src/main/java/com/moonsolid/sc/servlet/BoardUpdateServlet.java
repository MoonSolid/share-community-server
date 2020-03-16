package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.dao.BoardObjectFileDao;
import com.moonsolid.sc.domain.Board;

public class BoardUpdateServlet implements Servlet {

  BoardObjectFileDao boardDao;

  public BoardUpdateServlet(BoardObjectFileDao boardDao) {
    this.boardDao = boardDao;
  }


  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    Board board = (Board) in.readObject();

    if (boardDao.update(board) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시글이 없습니다.");
    }
  }


}
