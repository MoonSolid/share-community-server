package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.moonsolid.sc.dao.MemberObjectFileDao;
import com.moonsolid.sc.domain.Member;

public class MemberDetailServlet implements Servlet {

  MemberObjectFileDao memberDao;

  public MemberDetailServlet(MemberObjectFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Member member = memberDao.findByNo(no);

    if (member != null) {
      out.writeUTF("OK");
      out.writeObject(member);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
  }


}
