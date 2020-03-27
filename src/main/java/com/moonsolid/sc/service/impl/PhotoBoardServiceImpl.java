package com.moonsolid.sc.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.dao.PhotoFileDao;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.service.PhotoBoardService;

@Component
public class PhotoBoardServiceImpl implements PhotoBoardService {
  TransactionTemplate transactionTemplate;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardServiceImpl( //
      PlatformTransactionManager txManager, //
      PhotoBoardDao photoBoardDao, //
      PhotoFileDao photoFileDao) {
    this.transactionTemplate = new TransactionTemplate(txManager);
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Transactional
  @Override
  public void add(PhotoBoard photoBoard) throws Exception {
    if (photoBoardDao.insert(photoBoard) == 0) {
      throw new Exception("사진 게시글 등록에 실패했습니다.");
    }
    photoFileDao.insert(photoBoard);
  }

  @Override
  public List<PhotoBoard> listPlanPhoto(int planNo) throws Exception {
    return photoBoardDao.findAllByPlanNo(planNo);
  }

  @Override
  public PhotoBoard get(int no) throws Exception {
    return photoBoardDao.findByNo(no);
  }

  @Transactional
  @Override
  public void update(PhotoBoard photoBoard) throws Exception {
    if (photoBoardDao.update(photoBoard) == 0) {
      throw new Exception("사진 게시글 변경에 실패했습니다.");
    }
    if (photoBoard.getFiles() != null) {
      photoFileDao.deleteAll(photoBoard.getNo());
      photoFileDao.insert(photoBoard);
    }
  }

  @Transactional
  @Override
  public void delete(int no) throws Exception {
    photoFileDao.deleteAll(no);
    if (photoBoardDao.delete(no) == 0) {
      throw new Exception("해당 번호의 사진 게시글이 없습니다.");
    }
  }
}
