package com.moonsolid.sc.dao;

import java.util.List;
import com.moonsolid.sc.domain.Member;

public interface MemberDao {

  public int insert(Member member) throws Exception;

  public List<Member> findAll() throws Exception;

  public Member findByNo(int no) throws Exception;

  public int update(Member member) throws Exception;

  public int delete(int no) throws Exception;

  default List<Member> findByKeyword(String keyword) throws Exception {
    return null;
  }


}
