package com.moonsolid.sc.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.util.Prompt;

public class PlanSearchServlet implements Servlet {

  PlanDao planDao;

  public PlanSearchServlet(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    HashMap<String, Object> params = new HashMap<>();
    String keyword = Prompt.getString(in, out, "일정명 검색: ");
    if (keyword.length() > 0) {
      params.put("title", keyword);
    }

    keyword = Prompt.getString(in, out, "일정내용 검색: ");
    if (keyword.length() > 0) {
      params.put("description", keyword);
    }

    keyword = Prompt.getString(in, out, "일정장소 검색: ");
    if (keyword.length() > 0) {
      params.put("place", keyword);
    }

    keyword = Prompt.getString(in, out, "일정메모 검색: ");
    if (keyword.length() > 0) {
      params.put("memo", keyword);
    }

    keyword = Prompt.getString(in, out, "일정비용 검색: ");
    if (keyword.length() > 0) {
      params.put("cost", keyword);
    }

    out.println("------------------------------");
    out.println("[검색 결과]");
    out.println();

    List<Plan> plans = planDao.findByKeyword(params);
    for (Plan p : plans) {
      out.printf("%d,%s,%s,%s,%s,%s\n", p.getNo(), p.getTitle(), p.getDescription(), p.getPlace(),
          p.getMemo(), p.getCost());
    }
  }
}
