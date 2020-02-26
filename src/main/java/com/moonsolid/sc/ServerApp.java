package com.moonsolid.sc;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {
  public static void main(String[] args) {
    System.out.println("서버 스케쥴관리 시스템입니다.");

    try (ServerSocket serverSocket = new ServerSocket(9999)) {

      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결되었습니다!");

        processRequest(socket);

        System.out.println("--------------------------------------");
      }

    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
      return;
    }
  }

  static void processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;

        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String message = in.nextLine();
      System.out.println("클라이언트가 보낸 메시지를 수신하였습니다.");

      System.out.println("클라이언트: " + message);

      out.println("서버입니다.");
      System.out.println("클라이언트로 메시지를 전송하였습니다.");

    } catch (Exception e) {
      System.out.println("예외 발생:");
      e.printStackTrace();
    }
  }
}
