package com.moonsolid.sc.dao;

import java.util.List;
import com.moonsolid.sc.domain.PhotoBoard;

public interface PhotoBoardDao {
  int insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> findAllByPlanNo(int planNo) throws Exception;

  PhotoBoard findByNo(int no) throws Exception;

  int update(PhotoBoard photoBoard) throws Exception;

  int delete(int no) throws Exception;


}
