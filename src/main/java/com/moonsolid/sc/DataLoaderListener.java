package com.moonsolid.sc;

import java.util.Map;
import com.moonsolid.sc.context.ApplicationContextListener;
import com.moonsolid.sc.dao.json.BoardJsonFileDao;
import com.moonsolid.sc.dao.json.MemberJsonFileDao;
import com.moonsolid.sc.dao.json.PlanJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");

    BoardJsonFileDao boardDao = new BoardJsonFileDao("./board.json");
    MemberJsonFileDao memberDao = new MemberJsonFileDao("./member.json");
    PlanJsonFileDao planDao = new PlanJsonFileDao("./plan.json");


    context.put("boardDao", boardDao);
    context.put("memberDao", memberDao);
    context.put("planDao", planDao);

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}



}
