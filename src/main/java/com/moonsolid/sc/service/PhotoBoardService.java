package com.moonsolid.sc.service;

import java.util.List;
import com.moonsolid.sc.domain.PhotoBoard;

public interface PhotoBoardService {
  void add(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> listPlanPhoto(int planNo) throws Exception;

  PhotoBoard get(int no) throws Exception;

  void update(PhotoBoard photoBoard) throws Exception;

  void delete(int no) throws Exception;
}
