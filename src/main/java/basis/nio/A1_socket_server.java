package basis.nio;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1-0-2 socket简介-服务端demo
 * socket demo 服务端
 */
public class A1_socket_server {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000);
        System.out.println("服务端准备就绪");
        System.out.println("服务器信息 " + serverSocket.getInetAddress() + " Port=" + serverSocket.getLocalPort());
        // 等待客户端连接
        for (; ; ) {
            // 得到客户端
            Socket client = serverSocket.accept();
            // 客户端构造异步线程
            ClientHandler clientHandler = new ClientHandler(client);
            // 启动线程
            clientHandler.start();
        }
    }


    /**
     * 客户端消息处理
     */
    private static class ClientHandler extends Thread {
        private Socket socket;
        private boolean flag = true;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("新客户端连接：" + socket.getInetAddress() + " Port= " + socket.getPort());
            try {
                // 得到打印流 用于数据输出 ； 服务器会送消息用
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                // 得到输入流 用于接受数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                do {
                    // 客户端拿到一条数据
                    String str = socketInput.readLine();
                    if ("bye".equals(str)) {
                        flag = false;
                        socketOutput.println("bye");
                    } else {
                        System.out.println(str);
                        socketOutput.println("回送： " + str.length());
                    }
                } while (flag);
                socketInput.close();
                socketOutput.close();
            } catch (Exception e) {
                System.out.println("连接异常断开");
            } finally {// 连接关闭
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("客户端已退出：" + socket.getInetAddress() + " Port= " + socket.getPort());

            }
        }
    }
}
