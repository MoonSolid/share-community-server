package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.dao.MemberDao;
import com.moonsolid.sc.domain.Member;
import com.moonsolid.util.Prompt;

public class MemberSearchServlet implements Servlet {

  MemberDao memberDao;

  public MemberSearchServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    String keyword = Prompt.getString(in, out, "검색어 : ");

    List<Member> members = memberDao.findByKeyword(keyword);
    for (Member m : members) {
      out.printf("%d, %s, %s, %s, %s\n", //
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());



    }
  }
}
