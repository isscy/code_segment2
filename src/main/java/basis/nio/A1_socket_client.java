package basis.nio;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 1-0-1 socket简介-客户端demo
 * socket demo 客户端
 */
public class A1_socket_client {


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.setSoTimeout(3000); // 设置超时时间
        // 连接本机 2000端口 超时时间3000ms
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);
        System.out.println("已经发起向服务器的连接");
        System.out.println("客户端信息 " + socket.getLocalAddress() + " Port=" + socket.getLocalPort());
        System.out.println("服务器信息 " + socket.getInetAddress() + " Port=" + socket.getPort());
        try {
            sendMsg(socket);
        } catch (Exception e) {
            System.out.println("异常关闭");
        }
        // 释放资源
        socket.close();
        System.out.println("客户端已退出");
    }

    /**
     * 发送数据
     */
    private static void sendMsg(Socket client) throws IOException {
        // 键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        // 得到socket输出流，并转换为打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);
        //得到socket输入流 并转换为BufferedReader
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        boolean flag = true;
        do {
            // 键盘读取一行
            String str = input.readLine();
            // 发送到服务器
            socketPrintStream.println(str);
            // 从服务器读取一行
            String echo = socketBufferedReader.readLine();
            if ("bye".equals(echo)) {
                flag = false;
            } else {
                System.out.println("服务器返回：" + echo);
            }
        } while (flag);
        // 关闭流
        socketPrintStream.close();
        socketBufferedReader.close();


    }
}
