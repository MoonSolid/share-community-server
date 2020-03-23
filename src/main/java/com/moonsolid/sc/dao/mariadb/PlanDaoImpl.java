package com.moonsolid.sc.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sql.DataSource;

public class PlanDaoImpl implements PlanDao {

  DataSource dataSource;

  public PlanDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }


  @Override
  public int insert(Plan plan) throws Exception {
    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement(//
            "insert into sc_plan(place, cont, memo, cost)" + " values(?,?,?,?)")) {
      stmt.setString(1, plan.getPlace());
      stmt.setString(2, plan.getDescription());
      stmt.setString(3, plan.getMemo());
      stmt.setString(4, plan.getCost());
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Plan> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(//
            "select plan_id, place, cont, memo, cost" + " from sc_plan");
        ResultSet rs = stmt.executeQuery()) {
      ArrayList<Plan> list = new ArrayList<>();
      while (rs.next()) {
        Plan plan = new Plan();
        plan.setNo(rs.getInt("plan_id"));
        plan.setPlace(rs.getString("place"));
        plan.setDescription(rs.getString("cont"));
        plan.setMemo(rs.getString("memo"));
        plan.setCost(rs.getString("cost"));
        list.add(plan);
      }
      return list;
    }
  }

  @Override
  public Plan findByNo(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(//
            "select plan_id, place, cont, memo, cost" //
                + " from sc_plan" //
                + " where plan_id=?")) {
      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Plan plan = new Plan();
          plan.setNo(rs.getInt("plan_id"));
          plan.setPlace(rs.getString("place"));
          plan.setDescription(rs.getString("cont"));
          plan.setMemo(rs.getString("memo"));
          plan.setCost(rs.getString("cost"));
          return plan;

        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(Plan plan) throws Exception {
    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement(//
            "update sc_plan set " //
                + " place=?, cont=?, memo=?, cost=?" //
                + " where plan_id=?")) {
      stmt.setString(1, plan.getPlace());
      stmt.setString(2, plan.getDescription());
      stmt.setString(3, plan.getMemo());
      stmt.setString(4, plan.getCost());
      stmt.setInt(5, plan.getNo());
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection(); //
        PreparedStatement stmt = con.prepareStatement(//
            "delete from sc_plan" //
                + " where plan_id=?")) {
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

}
