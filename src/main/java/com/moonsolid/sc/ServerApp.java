package com.moonsolid.sc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.moonsolid.sc.context.ApplicationContextListener;
import com.moonsolid.sc.dao.BoardDao;
import com.moonsolid.sc.dao.MemberDao;
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
import com.moonsolid.sc.servlet.MemberUpdateServlet;
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

    try (ServerSocket serverSocket = new ServerSocket(9999)) {

      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었습니다.");


        executorService.submit(() -> {
          processRequest(socket);
          System.out.println("--------------------------------------");
        });
      }

    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }

    notifyApplicationDestroyed();

    executorService.shutdown();

  }

  int processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

      String request = in.readUTF();
      System.out.println("클라이언트가 보낸 메시지를 수신하였습니다.");


      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return 9;
      }

      Servlet servlet = servletMap.get(request);

      if (servlet != null) {
        try {
          servlet.service(in, out);

        } catch (Exception e) {
          out.writeUTF("FAIL");
          out.writeUTF(e.getMessage());

          System.out.println("클라이언트 요청 처리 중 오류 발생:");
          e.printStackTrace();
        }
      } else {
        notFound(out);
      }

      out.flush();
      System.out.println("클라이언트에 응답하였습니다.");
      return 0;

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
      return -1;
    }
  }


  private void notFound(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  public static void main(String[] args) {
    System.out.println("서버 스케쥴 관리 시스템입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}
