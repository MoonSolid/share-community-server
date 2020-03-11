package com.moonsolid.sc.domain;

import java.io.Serializable;

public class Schedule implements Serializable {

  private static final long serialVersionUID = 1L;
  int No;
  String place;
  String Description;
  String memo;
  String cost;



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((Description == null) ? 0 : Description.hashCode());
    result = prime * result + No;
    result = prime * result + ((cost == null) ? 0 : cost.hashCode());
    result = prime * result + ((memo == null) ? 0 : memo.hashCode());
    result = prime * result + ((place == null) ? 0 : place.hashCode());
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
    if (Description == null) {
      if (other.Description != null)
        return false;
    } else if (!Description.equals(other.Description))
      return false;
    if (No != other.No)
      return false;
    if (cost == null) {
      if (other.cost != null)
        return false;
    } else if (!cost.equals(other.cost))
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
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
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


}