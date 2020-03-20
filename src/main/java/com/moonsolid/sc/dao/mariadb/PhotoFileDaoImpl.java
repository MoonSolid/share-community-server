package com.moonsolid.sc.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.moonsolid.sc.dao.PhotoFileDao;
import com.moonsolid.sc.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {

  String jdbcUrl;
  String username;
  String password;

  public PhotoFileDaoImpl(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate( //
          "insert into sc_photo_file(photo_id,file_path) values(" //
              + photoFile.getBoardNo() + ", '" + photoFile.getFilepath() //
              + "')");

      return result;
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(//
            "select photo_file_id, photo_id, file_path" //
                + " from sc_photo_file" //
                + " where photo_id=" + boardNo //
                + " order by photo_file_id asc")) {

      ArrayList<PhotoFile> list = new ArrayList<>();
      while (rs.next()) {
        list.add(new PhotoFile() //
            .setNo(rs.getInt("photo_file_id")) //
            .setFilepath(rs.getString("file_path")) //
            .setBoardNo(rs.getInt("photo_id")));
      }
      return list;
    }
  }

  @Override
  public int update(PhotoFile photoFile) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate( //
          "update sc_photo_file set photo_file_id='" //
              + photoFile.getBoardNo() //
              + "' where photo_id=" + photoFile.getNo());
      return result;
    }
  }

  @Override
  public int deleteAll(int boardNo) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate( //
          "delete from sc_photo_file" //
              + " where photo_id=" + boardNo);
      return result;
    }
  }

}
