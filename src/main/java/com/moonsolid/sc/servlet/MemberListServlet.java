package com.moonsolid.sc.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.moonsolid.sc.domain.Member;

public class MemberListServlet {

  List<Member> members;

  public MemberListServlet(List<Member> members) {
    this.members = members;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {

    out.writeUTF("OK");
    out.reset();
    out.writeObject(members);

  }



}
