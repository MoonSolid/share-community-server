package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.domain.Member;
import com.moonsolid.sc.service.MemberService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;
import com.moonsolid.util.RequestMapping;

@Component
public class MemberDetailServlet {

  MemberService memberService;

  public MemberDetailServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/detail")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "회원번호 : ");
    Member member = memberService.get(no);

    if (member != null) {
      out.printf("번호: %d\n", member.getNo());
      out.printf("이름: %s\n", member.getName());
      out.printf("이메일: %s\n", member.getEmail());
      out.printf("암호: %s\n", member.getPassword());
      out.printf("사진: %s\n", member.getPhoto());
      out.printf("전화: %s\n", member.getTel());

    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }


}
