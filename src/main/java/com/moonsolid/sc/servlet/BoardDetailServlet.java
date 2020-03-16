package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.domain.Board;

public class BoardDetailServlet implements Servlet {

  BoardDao boardDao;

  public BoardDetailServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Board board = boardDao.findByNo(no);

    if (board != null) {
      out.writeUTF("OK");
      out.writeObject(board);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시글이 없습니다.");
    }
  }



}
