//package mj;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
///**
// * @author: liyangjin
// * @create: 2023-06-20 10:42
// * @Description:
// */
//public class MyServer {
//
//    public static void main(String[] args) throws Exception {
//        Server server = new Server(8080);
//
//        server.setHandler(new AbstractHandler() {
//            public void handle(String target, Request baseRequest, HttpServletRequest request,
//                               HttpServletResponse response) throws IOException, ServletException {
//                if ("/callback".equals(target) && "POST".equalsIgnoreCase(request.getMethod())) {
//                    // 读取请求体
//                    StringBuilder requestBody = new StringBuilder();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        requestBody.append(line);
//                    }
//
//                    // 打印请求体
//                    System.out.println("Received request body: " + requestBody.toString());
//
//                    // 设置响应
//                    response.setContentType("text/html;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_OK);
//                    baseRequest.setHandled(true);
//                    response.getWriter().println("Callback received!"); // 返回示例响应
//                }
//            }
//        });
//
//        server.start();
//        server.join();
//    }
//}
