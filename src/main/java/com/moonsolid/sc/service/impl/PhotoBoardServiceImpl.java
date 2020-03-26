package com.moonsolid.sc.service.impl;

import java.util.List;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.dao.PhotoFileDao;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.service.PhotoBoardService;
import com.moonsolid.sql.PlatformTransactionManager;
import com.moonsolid.sql.TransactionCallback;
import com.moonsolid.sql.TransactionTemplate;
import com.moonsolid.util.Component;

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

  @Override
  public void add(PhotoBoard photoBoard) throws Exception {
    transactionTemplate.execute(new TransactionCallback() {
      @Override
      public Object doInTransaction() throws Exception {
        if (photoBoardDao.insert(photoBoard) == 0) {
          throw new Exception("사진 게시글 등록에 실패했습니다.");
        }
        photoFileDao.insert(photoBoard);
        return null;
      }
    });
  }

  @Override
  public List<PhotoBoard> listPlanPhoto(int planNo) throws Exception {
    return photoBoardDao.findAllByPlanNo(planNo);
  }

  @Override
  public PhotoBoard get(int no) throws Exception {
    return photoBoardDao.findByNo(no);
  }

  @Override
  public void update(PhotoBoard photoBoard) throws Exception {
    transactionTemplate.execute(() -> {
      if (photoBoardDao.update(photoBoard) == 0) {
        throw new Exception("사진 게시글 변경에 실패했습니다.");
      }
      if (photoBoard.getFiles() != null) {
        photoFileDao.deleteAll(photoBoard.getNo());
        photoFileDao.insert(photoBoard);
      }
      return null;
    });
  }

  @Override
  public void delete(int no) throws Exception {
    transactionTemplate.execute(() -> {
      photoFileDao.deleteAll(no);
      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("해당 번호의 사진 게시글이 없습니다.");
      }
      return null;
    });
  }
}
