package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.moonsolid.sc.domain.Member;
import com.moonsolid.sc.service.MemberService;
import com.moonsolid.util.Component;
import com.moonsolid.util.Prompt;

@Component("/member/add")
public class MemberAddServlet implements Servlet {

  MemberService memberService;

  public MemberAddServlet(MemberService memberService) {
    this.memberService = memberService;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Member member = new Member();

    member.setName(Prompt.getString(in, out, "회원이름 : "));
    member.setEmail(Prompt.getString(in, out, "이메일 : "));
    member.setPassword(Prompt.getString(in, out, "비밀번호 : "));
    member.setPhoto(Prompt.getString(in, out, "사진 : "));
    member.setTel(Prompt.getString(in, out, "전화번호 : "));

    if (memberService.add(member) > 0) {
      out.println("회원을 저장했습니다.");

    } else {
      out.println("저장에 실패했습니다.");
    }
  }
}
