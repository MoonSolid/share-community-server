package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.dao.PhotoFileDao;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.domain.PhotoFile;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sql.PlatformTransactionManager;
import com.moonsolid.sql.TransactionCallback;
import com.moonsolid.sql.TransactionTemplate;
import com.moonsolid.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {

  TransactionTemplate transactionTemplate;
  PhotoBoardDao photoBoardDao;
  PlanDao planDao;
  PhotoFileDao photoFileDao;


  public PhotoBoardAddServlet(//
      PlatformTransactionManager txManager, //
      PhotoBoardDao photoBoardDao, //
      PlanDao planDao, //
      PhotoFileDao photoFileDao) {
    this.transactionTemplate = new TransactionTemplate(txManager);
    this.photoBoardDao = photoBoardDao;
    this.planDao = planDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "제목 : "));

    int planNo = Prompt.getInt(in, out, "일정번호 : ");

    Plan plan = planDao.findByNo(planNo);
    if (plan == null) {
      out.println("일정 번호가 유효하지 않습니다.");
    }

    photoBoard.setPlan(plan);

    List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
    photoBoard.setFiles(photoFiles);


    transactionTemplate.execute(new TransactionCallback() {
      @Override
      public Object doInTransaction() throws Exception {

        if (photoBoardDao.insert(photoBoard) == 0) {
          throw new Exception("사진 게시글 등록에 실패했습니다.");
        }
        photoFileDao.insert(photoBoard);
        out.println("새 사진 게시글을 등록했습니다.");

        return null;
      }
    });
  }

  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {
    out.println("최소 한개의 사진파일을 등록해야 합니다.");
    out.println("파일명 입력 없이  엔터를 치면 파일 추가를 마칩니다.");

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();

    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일?");

      if (filepath.length() == 0) {
        if (photoFiles.size() > 0) {
          break;
        } else {
          out.println("최소 한개의 사진파일을 등록해야 합니다.");
          continue;
        }
      }
      photoFiles.add(new PhotoFile().setFilepath(filepath));
    }
    return photoFiles;
  }
}
