package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.domain.Member;
import com.moonsolid.sc.service.MemberService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;
import com.moonsolid.util.RequestMapping;

@Component
public class MemberSearchServlet implements Servlet {

  MemberService memberService;

  public MemberSearchServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/search")
  public void service(Scanner in, PrintStream out) throws Exception {
    String keyword = Prompt.getString(in, out, "검색어 : ");

    List<Member> members = memberService.search(keyword);
    for (Member m : members) {
      out.printf("%d, %s, %s, %s, %s\n", //
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());



    }
  }
}
