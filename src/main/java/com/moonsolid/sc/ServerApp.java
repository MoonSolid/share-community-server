package com.moonsolid.sc;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.moonsolid.sc.context.ApplicationContextListener;
import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.dao.MemberDao;
import com.moonsolid.sc.dao.PhotoBoardDao;
import com.moonsolid.sc.dao.PhotoFileDao;
import com.moonsolid.sc.dao.PlanDao;
import com.moonsolid.sc.servlet.BoardAddServlet;
import com.moonsolid.sc.servlet.BoardDeleteServlet;
import com.moonsolid.sc.servlet.BoardDetailServlet;
import com.moonsolid.sc.servlet.BoardListServlet;
import com.moonsolid.sc.servlet.BoardUpdateServlet;
import com.moonsolid.sc.servlet.MemberAddServlet;
import com.moonsolid.sc.servlet.MemberDeleteServlet;
import com.moonsolid.sc.servlet.MemberDetailServlet;
import com.moonsolid.sc.servlet.MemberListServlet;
import com.moonsolid.sc.servlet.MemberSearchServlet;
import com.moonsolid.sc.servlet.MemberUpdateServlet;
import com.moonsolid.sc.servlet.PhotoBoardAddServlet;
import com.moonsolid.sc.servlet.PhotoBoardDeleteServlet;
import com.moonsolid.sc.servlet.PhotoBoardDetailServlet;
import com.moonsolid.sc.servlet.PhotoBoardListServlet;
import com.moonsolid.sc.servlet.PhotoBoardUpdateServlet;
import com.moonsolid.sc.servlet.PlanAddServlet;
import com.moonsolid.sc.servlet.PlanDeleteServlet;
import com.moonsolid.sc.servlet.PlanDetailServlet;
import com.moonsolid.sc.servlet.PlanListServlet;
import com.moonsolid.sc.servlet.PlanUpdateServlet;
import com.moonsolid.sc.servlet.Servlet;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  Map<String, Servlet> servletMap = new HashMap<>();

  ExecutorService executorService = Executors.newCachedThreadPool();

  boolean serverStop = false;

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service() {

    notifyApplicationInitialized();

    BoardDao boardDao = (BoardDao) context.get("boardDao");
    PlanDao planDao = (PlanDao) context.get("planDao");
    MemberDao memberDao = (MemberDao) context.get("memberDao");
    PhotoBoardDao photoBoardDao = (PhotoBoardDao) context.get("photoBoardDao");
    PhotoFileDao photoFileDao = (PhotoFileDao) context.get("photoFileDao");

    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/update", new BoardUpdateServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));

    servletMap.put("/plan/list", new PlanListServlet(planDao));
    servletMap.put("/plan/add", new PlanAddServlet(planDao));
    servletMap.put("/plan/detail", new PlanDetailServlet(planDao));
    servletMap.put("/plan/update", new PlanUpdateServlet(planDao));
    servletMap.put("/plan/delete", new PlanDeleteServlet(planDao));

    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/update", new MemberUpdateServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));
    servletMap.put("/member/search", new MemberSearchServlet(memberDao));

    servletMap.put("/photoboard/list", new PhotoBoardListServlet( //
        photoBoardDao, planDao));

    servletMap.put("/photoboard/detail", new PhotoBoardDetailServlet( //
        photoBoardDao, photoFileDao));

    servletMap.put("/photoboard/add", new PhotoBoardAddServlet( //
        photoBoardDao, planDao, photoFileDao));

    servletMap.put("/photoboard/update", new PhotoBoardUpdateServlet( //
        photoBoardDao, photoFileDao));

    servletMap.put("/photoboard/delete", new PhotoBoardDeleteServlet( //
        photoBoardDao, photoFileDao));



    try (ServerSocket serverSocket = new ServerSocket(9999)) {

      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었습니다.");


        executorService.submit(() -> {
          processRequest(socket);
          System.out.println("--------------------------------------");
        });

        if (serverStop) {
          break;
        }

      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생");
    }


    executorService.shutdown();

    while (true) {
      if (executorService.isTerminated()) {
        break;
      }
      try {
        Thread.sleep(500);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    notifyApplicationDestroyed();

    System.out.println("서버 종료");
  }

  void processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      System.out.printf("=> %s\n", request);

      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }

      Servlet servlet = servletMap.get(request);

      if (servlet != null) {
        try {
          servlet.service(in, out);

        } catch (Exception e) {
          out.println("요청 처리 중 오류 발생!");
          out.println(e.getMessage());

          System.out.println("클라이언트 요청 처리 중 오류 발생:");
          e.printStackTrace();
        }
      } else {
        notFound(out);
      }

      out.println("!end!");
      out.flush();
      System.out.println("클라이언트에게 응답하였습니다.");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }
  }


  private void notFound(PrintStream out) throws IOException {
    out.println("요청한 명령을 처리할 수 없습니다.");
  }

  private void quit(PrintStream out) throws IOException {
    serverStop = true;
    out.println("OK");
    out.println("!end!");
    out.flush();
  }

  public static void main(String[] args) {
    System.out.println("서버 스케쥴 관리 시스템입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}
