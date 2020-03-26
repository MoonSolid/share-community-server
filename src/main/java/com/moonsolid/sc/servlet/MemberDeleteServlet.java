package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.service.MemberService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;
import com.moonsolid.util.RequestMapping;

@Component
public class MemberDeleteServlet {

  MemberService memberService;

  public MemberDeleteServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/delete")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "회원번호 : ");

    if (memberService.delete(no) > 0) {
      out.println("회원을 삭제했습니다.");

    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }
}
