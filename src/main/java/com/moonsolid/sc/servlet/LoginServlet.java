package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.Member;
import com.moonsolid.sc.service.MemberService;
import com.moonsolid.util.Prompt;
import com.moonsolid.util.RequestMapping;

@Component
public class LoginServlet {
  MemberService memberService;

  public LoginServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/auth/login")
  public void service(Scanner in, PrintStream out) throws Exception {
    String email = Prompt.getString(in, out, "이메일? ");
    String password = Prompt.getString(in, out, "암호? ");

    Member member = memberService.get(email, password);

    if (member != null) {
      out.printf("'%s'님 환영합니다.\n", member.getName());
    } else {
      out.println("사용자 정보가 유효하지 않습니다.");
    }
  }
}
