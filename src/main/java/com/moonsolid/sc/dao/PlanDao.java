package com.moonsolid.sc.dao;

import java.util.List;
import java.util.Map;
import com.moonsolid.sc.domain.Plan;

public interface PlanDao {

  int insert(Plan plan) throws Exception;

  List<Plan> findAll() throws Exception;

  Plan findByNo(int no) throws Exception;

  int update(Plan plan) throws Exception;

  int delete(int no) throws Exception;

  List<Plan> findByKeyword(Map<String, Object> params) throws Exception;
}
