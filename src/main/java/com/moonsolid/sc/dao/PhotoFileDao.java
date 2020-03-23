package com.moonsolid.sc.dao;

import java.util.List;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.domain.PhotoFile;

public interface PhotoFileDao {
  int insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoFile> findAll(int boardNo) throws Exception;

  int deleteAll(int boardNo) throws Exception;
}
