package com.moonsolid.sc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.moonsolid.sc.context.ApplicationContextListener;
import com.moonsolid.sc.domain.Board;
import com.moonsolid.sc.domain.Member;
import com.moonsolid.sc.domain.Plan;

public class DataLoaderListener implements ApplicationContextListener {

  List<Member> memberList = new ArrayList<>();
  List<Board> boardList = new ArrayList<>();
  List<Plan> planList = new ArrayList<>();


  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");
    loadBoardData();
    loadMemberData();
    loadPlanData();

    context.put("boardList", boardList);
    context.put("memberList", memberList);
    context.put("planList", planList);

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");

    saveBoardData();
    saveMemberData();
    savePlanData();
  }

  @SuppressWarnings("unchecked")
  private void loadMemberData() {
    File file = new File("./member.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      memberList = (List<Member>) in.readObject();
      System.out.printf("총 %d 개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveMemberData() {
    File file = new File("./member.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(memberList);
      System.out.printf("총 %d 개의 회원 데이터를 저장했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBoardData() {
    File file = new File("./board.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      boardList = (List<Board>) in.readObject();

      System.out.printf("총 %d 개의 게시물 데이터를 로딩했습니다.\n", boardList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveBoardData() {
    File file = new File("./board.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(boardList);
      System.out.printf("총 %d 개의 게시물 데이터를 저장했습니다.\n", boardList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void loadPlanData() {
    File file = new File("./plan.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      planList = (List<Plan>) in.readObject();
      System.out.printf("총 %d 개의 일정 데이터를 로딩했습니다.\n", planList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void savePlanData() {
    File file = new File("./plan.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(planList);
      System.out.printf("총 %d 개의 일정 데이터를 저장했습니다.\n", planList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }


}
