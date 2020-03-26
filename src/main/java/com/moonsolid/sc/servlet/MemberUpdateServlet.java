package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.moonsolid.sc.domain.Member;
import com.moonsolid.sc.service.MemberService;
import com.moonsolid.util.Prompt;
import com.moonsolid.util.RequestMapping;

@Component
public class MemberUpdateServlet {

  MemberService memberService;

  public MemberUpdateServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/update")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "회원번호 : ");

    Member old = memberService.get(no);
    if (old == null) {
      out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member member = new Member();

    member.setNo(no);
    member.setName(Prompt.getString(in, out, //
        String.format("회원이름(기존 이름 : %s): \n", old.getName()), old.getName()));
    member.setEmail(Prompt.getString(in, out, //
        String.format("이메일(기존 이메일 : %s): \n", old.getEmail()), old.getEmail()));
    member.setPassword(Prompt.getString(in, out, //
        String.format("암호(기존 암호 : %s): \n", old.getPassword()), old.getPassword()));
    member.setPhoto(Prompt.getString(in, out, //
        String.format("사진(기존사진 : %s): \n", old.getPhoto()), old.getPhoto()));
    member.setTel(Prompt.getString(in, out, //
        String.format("전화번호(기존전화 : %s): \n", old.getTel()), old.getTel()));

    if (memberService.update(member) > 0) {
      out.println("회원을 변경했습니다.");

    } else {
      out.println("회원 변경에 실패했습니다.");
    }
  }

}
