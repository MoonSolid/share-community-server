package com.moonsolid.sc;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ConnectionClosedException;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ExceptionListener;
import org.apache.hc.core5.http.HttpConnection;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.impl.bootstrap.HttpServer;
import org.apache.hc.core5.http.impl.bootstrap.ServerBootstrap;
import org.apache.hc.core5.http.io.HttpRequestHandler;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.util.TimeValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import com.moonsolid.sc.context.ApplicationContextListener;
import com.moonsolid.util.RequestHandler;
import com.moonsolid.util.RequestMappingHandlerMapping;

public class ServerApp implements ExceptionListener, HttpRequestHandler {

  static Logger logger = LogManager.getLogger(ServerApp.class);

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  ApplicationContext iocContainer;

  RequestMappingHandlerMapping handlerMapper;

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

  public void service() throws Exception {
    notifyApplicationInitialized();
    iocContainer = (ApplicationContext) context.get("iocContainer");

    handlerMapper = //
        (RequestMappingHandlerMapping) context.get("handlerMapper");

    SocketConfig socketConfig = SocketConfig.custom() //
        .setSoTimeout(15, TimeUnit.SECONDS)//
        .setTcpNoDelay(true) //
        .build();

    HttpServer server = ServerBootstrap.bootstrap()//
        .setListenerPort(9999) //
        .setSocketConfig(socketConfig) //
        .setSslContext(null) //
        .setExceptionListener(this) //
        .register("*", this) //
        .create(); //

    server.start();

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        notifyApplicationDestroyed();
        logger.info("서버 종료");
        server.close(CloseMode.GRACEFUL);
      }
    });

    logger.info("서버 시작");
    server.awaitTermination(TimeValue.MAX_VALUE);
  }

  private void notFound(PrintWriter out) throws IOException {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>실행 오류!</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>실행 오류!</h1>");
    out.println("<p>요청한 명령을 처리할 수 없습니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }

  private void error(PrintWriter out, Exception ex) throws IOException {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>실행 오류!</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>실행 오류!</h1>");
    out.printf("<p>%s</p>\n", ex.getMessage());
    out.println("</body>");
    out.println("</html>");
  }

  private String getServletPath(String requestUri) {
    return requestUri.split("\\?")[0];
  }

  private Map<String, String> getParameters(String requestUri) throws Exception {
    Map<String, String> params = new HashMap<>();
    String[] items = requestUri.split("\\?");
    if (items.length > 1) {
      logger.debug(String.format("query string => %s", items[1]));
      String[] entries = items[1].split("&");
      for (String entry : entries) {
        logger.debug(String.format("parameter => %s", entry));
        String[] kv = entry.split("=");

        if (kv.length > 1) {
          String value = URLDecoder.decode(kv[1], "UTF-8");

          params.put(kv[0], value);
        } else {
          params.put(kv[0], "");
        }
      }
    }
    return params;
  }

  @Override
  public void onError(final Exception ex) {
    ex.printStackTrace();
  }

  @Override
  public void onError(final HttpConnection conn, final Exception ex) {
    if (ex instanceof SocketTimeoutException) {
      System.err.println("Connection timed out");
    } else if (ex instanceof ConnectionClosedException) {
      System.err.println(ex.getMessage());
    } else {
      ex.printStackTrace();
    }
  }

  @Override
  public void handle( //
      final ClassicHttpRequest request, //
      final ClassicHttpResponse response, //
      final HttpContext context //
  ) throws HttpException, IOException {

    logger.info("--------------------------------------");
    logger.info("클라이언트의 요청이 들어옴");

    StringWriter outBuffer = new StringWriter();
    PrintWriter out = new PrintWriter(outBuffer);

    String method = request.getMethod(); //
    String requestUri = request.getPath(); //
    logger.info("{} {}", method, requestUri);

    try {
      String servletPath = getServletPath(requestUri);
      logger.debug(String.format("servlet path => %s", servletPath));

      Map<String, String> params = getParameters(requestUri);
      RequestHandler requestHandler = handlerMapper.getHandler(servletPath);

      if (requestHandler != null) {
        requestHandler.getMethod().invoke( //
            requestHandler.getBean(), //
            params, out);

      } else {
        notFound(out);
        logger.info("해당 명령을 지원하지 않습니다.");
      }
    } catch (Exception e) {
      error(out, e);

      logger.info("클라이언트 요청 처리 중 오류 발생");
      logger.info(e.getMessage());
      StringWriter strWriter = new StringWriter();
      e.printStackTrace(new PrintWriter(strWriter));
      logger.debug(strWriter.toString());
    }

    response.setCode(HttpStatus.SC_OK); //
    response.setEntity(new StringEntity(//
        outBuffer.toString(), //
        ContentType.create("text/html", Charset.forName("UTF-8"))));
    logger.info("클라이언트에게 응답하였음!");
  }


  public static void main(String[] args) throws Exception {
    logger.info("서버 스케쥴 관리 시스템입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();
  }
}
