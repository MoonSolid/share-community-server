package com.moonsolid.sc.dao;

import java.util.List;
import com.moonsolid.sc.domain.Plan;

public class PlanObjectFileDao extends AbstractObjectFileDao<Plan> implements PlanDao {

  public PlanObjectFileDao(String filename) {
    super(filename);
  }

  @Override
  public int insert(Plan plan) throws Exception {


    if (indexOf(plan.getNo()) > -1) {
      return 0;
    }

    list.add(plan);
    saveData();
    return 1;
  }

  @Override
  public List<Plan> findAll() throws Exception {
    return list;
  }

  @Override
  public Plan findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  @Override
  public int update(Plan plan) throws Exception {
    int index = indexOf(plan.getNo());

    if (index == -1) {
      return 0;
    }
    list.set(index, plan);
    saveData();
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }
    list.remove(index);
    saveData();
    return 1;
  }

  @Override
  protected <K> int indexOf(K key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key) {
        return i;
      }
    }
    return -1;
  }



}
