package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.dao.MemberDao;
import com.moonsolid.sc.domain.Member;

public class MemberAddServlet implements Servlet {

  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Member member = new Member();

    out.println("회원이름 : \n!{}!");
    member.setName(in.nextLine());

    out.println("회원이메일 : \n!{}!");
    member.setEmail(in.nextLine());

    out.println("암호 : \n!{}!");
    member.setPassword(in.nextLine());

    out.println("사진 : \n!{}!");
    member.setPhoto(in.nextLine());

    out.println("전화 : \n!{}!");
    member.setTel(in.nextLine());

    if (memberDao.insert(member) > 0) {
      out.println("회원을 등록했습니다.");

    } else {
      out.println("회원 등록에 실패했습니다.");
    }
  }
}
