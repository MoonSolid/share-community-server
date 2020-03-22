package com.moonsolid.sc.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.domain.PhotoBoard;
import com.moonsolid.sc.domain.Plan;
import com.moonsolid.sql.DataSource;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  DataSource dataSource;

  public PhotoBoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate( //
          "insert into sc_photo(titl,plan_id) values('" //
              + photoBoard.getTitle() + "', " + photoBoard.getPlan().getNo() //
              + ")", //
          Statement.RETURN_GENERATED_KEYS);
      try (ResultSet generatedKeySet = stmt.getGeneratedKeys()) {
        generatedKeySet.next();
        photoBoard.setNo(generatedKeySet.getInt(1));
      }
      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAllByPlanNo(int planNo) throws Exception {
    try (Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(//
            "select photo_id, titl, cdt, vw_cnt, plan_id" //
                + " from sc_photo" //
                + " where plan_id=" + planNo //
                + " order by photo_id desc")) {

      ArrayList<PhotoBoard> list = new ArrayList<>();

      while (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setCreatedDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        list.add(photoBoard);
      }

      return list;
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(//
            "select" //
                + " p.photo_id," //
                + " p.titl," //
                + " p.cdt," //
                + " p.vw_cnt,"//
                + " l.plan_id,"//
                + " l.place plan_place" //
                + " from sc_photo p" //
                + " inner join sc_plan l on p.plan_id=l.plan_id" //
                + " where photo_id=" + no)) {

      if (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setCreatedDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        Plan plan = new Plan();
        plan.setNo(rs.getInt("plan_id"));
        plan.setPlace(rs.getString("plan_place"));

        photoBoard.setPlan(plan);

        return photoBoard;

      } else {
        return null;
      }
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate( //
          "update sc_photo set titl='" //
              + photoBoard.getTitle() //
              + "' where photo_id=" + photoBoard.getNo());
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection(); Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate( //
          "delete from sc_photo" //
              + " where photo_id=" + no);
      return result;
    }
  }

}
