package com.moonsolid.sc.service.impl;

import java.util.HashMap;
import java.util.List;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sc.service.PlanService;

public class PlanServiceImpl implements PlanService {
  PlanDao planDao;

  public PlanServiceImpl(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public Plan get(int no) throws Exception {
    return planDao.findByNo(no);
  }

  @Override
  public int add(Plan plan) throws Exception {
    return planDao.insert(plan);
  }

  @Override
  public int delete(int no) throws Exception {
    return planDao.delete(no);
  }

  @Override
  public List<Plan> list() throws Exception {
    return planDao.findAll();
  }

  @Override
  public List<Plan> search(HashMap<String, Object> params) throws Exception {
    return planDao.findByKeyword(params);
  }

  @Override
  public int update(Plan plan) throws Exception {
    return planDao.update(plan);
  }

}
