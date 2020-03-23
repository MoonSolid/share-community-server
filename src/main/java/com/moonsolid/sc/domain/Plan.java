package com.moonsolid.sc.domain;

import java.io.Serializable;

public class Plan implements Serializable {

  private static final long serialVersionUID = 20200313L;

  private int no;
  private String place;
  private String description;
  private String memo;
  private String cost;
  private String title;

  @Override
  public String toString() {
    return "Plan [no=" + no + ", place=" + place + ", description=" + description + ", memo=" + memo
        + ", cost=" + cost + ", title=" + title + "]";
  }


  public static Plan valueOf(String csv) {
    String[] data = csv.split(",");
    Plan plan = new Plan();
    plan.setNo(Integer.parseInt(data[0]));
    plan.setPlace(data[1]);
    plan.setDescription(data[2]);
    plan.setMemo(data[3]);
    plan.setCost(data[4]);
    plan.setTitle(data[5]);

    return plan;
  }


  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s\n", this.getNo(), this.getPlace(),
        this.getDescription(), this.getMemo(), this.getCost(), this.getTitle());
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cost == null) ? 0 : cost.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((memo == null) ? 0 : memo.hashCode());
    result = prime * result + no;
    result = prime * result + ((place == null) ? 0 : place.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
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
    Plan other = (Plan) obj;
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
    if (no != other.no)
      return false;
    if (place == null) {
      if (other.place != null)
        return false;
    } else if (!place.equals(other.place))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
  }


  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
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


}
