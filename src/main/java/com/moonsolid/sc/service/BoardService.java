package com.moonsolid.sc.service;

import java.util.List;
import com.moonsolid.sc.domain.Board;

public interface BoardService {
  void add(Board board) throws Exception;

  List<Board> list() throws Exception;

  int delete(int no) throws Exception;

  Board get(int no) throws Exception;

  int update(Board board) throws Exception;
}
