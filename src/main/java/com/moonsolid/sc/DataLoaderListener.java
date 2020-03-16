package com.moonsolid.sc;

import java.util.Map;
import com.moonsolid.sc.context.ApplicationContextListener;
import com.moonsolid.sc.dao.BoardObjectFileDao;
import com.moonsolid.sc.dao.MemberObjectFileDao;
import com.moonsolid.sc.dao.PlanObjectFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");

    BoardObjectFileDao boardDao = new BoardObjectFileDao("./board.ser2");
    MemberObjectFileDao memberDao = new MemberObjectFileDao("./member.ser2");
    PlanObjectFileDao planDao = new PlanObjectFileDao("./plan.ser2");


    context.put("boardDao", boardDao);
    context.put("memberDao", memberDao);
    context.put("planDao", planDao);

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}



}
