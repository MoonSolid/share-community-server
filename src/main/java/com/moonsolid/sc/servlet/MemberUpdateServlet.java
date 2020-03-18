package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.dao.MemberDao;
import com.moonsolid.sc.domain.Member;

public class MemberUpdateServlet implements Servlet {

  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("회원번호 : ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());


    Member old = memberDao.findByNo(no);
    if (old == null) {
      out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member member = new Member();

    member.setNo(no);

    out.printf("이름(기존 회원이름%s): \n", old.getName());
    out.println("!{}!");
    out.flush();
    member.setName(in.nextLine());

    out.printf("이메일(기존 이메일%s): \n", old.getEmail());
    out.println("!{}!");
    out.flush();
    member.setEmail(in.nextLine());

    out.printf("암호(기존 암호%s): \n", old.getPassword());
    out.println("!{}!");
    out.flush();
    member.setPassword(in.nextLine());

    out.printf("사진(기존 사진%s): \n", old.getPhoto());
    out.println("!{}!");
    out.flush();
    member.setPhoto(in.nextLine());

    out.printf("전화(기존 전화%s): \n", old.getTel());
    out.println("!{}!");
    out.flush();
    member.setTel(in.nextLine());

    if (memberDao.update(member) > 0) {
      out.println("회원을 변경했습니다.");

    } else {
      out.println("회원 변경에 실패했습니다.");
    }
  }

}
