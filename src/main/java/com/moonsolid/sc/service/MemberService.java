package com.moonsolid.sc.service;

import java.util.List;
import com.moonsolid.sc.domain.Member;

public interface MemberService {
  List<Member> list() throws Exception;

  int delete(int no) throws Exception;

  int add(Member member) throws Exception;

  Member get(int no) throws Exception;

  Member get(String email, String password) throws Exception;

  List<Member> search(String keyword) throws Exception;

  int update(Member member) throws Exception;
}
