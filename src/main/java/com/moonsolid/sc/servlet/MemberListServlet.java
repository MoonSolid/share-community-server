package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.Member;
import com.moonsolid.sc.service.MemberService;
import com.moonsolid.util.RequestMapping;

@Component
public class MemberListServlet {

  MemberService memberService;

  public MemberListServlet(MemberService memberService) {
    this.memberService = memberService;
  }


  @RequestMapping("/member/list")
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Member> members = memberService.list();
    for (Member m : members) {
      out.printf("%d, %s, %s, %s, %s\n", //
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());
    }
  }
}
