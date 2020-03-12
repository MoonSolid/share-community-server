package com.moonsolid.sc.domain;

import java.io.Serializable;

public class Schedule implements Serializable {

  private static final long serialVersionUID = 20200311L;

  private int No;
  private String place;
  private String description;
  private String memo;
  private String cost;
  private String scheduleTime;

  public static Schedule valueOf(String csv) {
    String[] data = csv.split(",");
    Schedule schedule = new Schedule();
    schedule.setNo(Integer.parseInt(data[0]));
    schedule.setPlace(data[1]);
    schedule.setDescription(data[2]);
    schedule.setMemo(data[3]);
    schedule.setCost(data[4]);
    schedule.setScheduleTime(data[5]);
    return schedule;
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s\n", this.getNo(), this.getPlace(),
        this.getDescription(), this.getMemo(), this.getCost(), this.getScheduleTime());
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + No;
    result = prime * result + ((cost == null) ? 0 : cost.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((memo == null) ? 0 : memo.hashCode());
    result = prime * result + ((place == null) ? 0 : place.hashCode());
    result = prime * result + ((scheduleTime == null) ? 0 : scheduleTime.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Schedule other = (Schedule) obj;
    if (No != other.No)
      return false;
    if (cost == null) {
      if (other.cost != null)
        return false;
    } else if (!cost.equals(other.cost))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (memo == null) {
      if (other.memo != null)
        return false;
    } else if (!memo.equals(other.memo))
      return false;
    if (place == null) {
      if (other.place != null)
        return false;
    } else if (!place.equals(other.place))
      return false;
    if (scheduleTime == null) {
      if (other.scheduleTime != null)
        return false;
    } else if (!scheduleTime.equals(other.scheduleTime))
      return false;
    return true;
  }

  public int getNo() {
    return No;
  }

  public void setNo(int no) {
    No = no;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getCost() {
    return cost;
  }

  public void setCost(String cost) {
    this.cost = cost;
  }

  public String getScheduleTime() {
    return scheduleTime;
  }

  public void setScheduleTime(String scheduleTime) {
    this.scheduleTime = scheduleTime;
  }



}
