package com.moonsolid.sc.service;

import java.util.HashMap;
import java.util.List;
import com.moonsolid.sc.domain.Plan;

public interface PlanService {
  Plan get(int no) throws Exception;

  int add(Plan plan) throws Exception;

  int delete(int no) throws Exception;

  List<Plan> list() throws Exception;

  List<Plan> search(HashMap<String, Object> params) throws Exception;

  int update(Plan plan) throws Exception;
}
