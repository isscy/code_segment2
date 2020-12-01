package basis.nio;

import java.io.*;
import java.net.*;

/**
 * 1-0-0 socket简介
 * socket TCP UDP
 * 2020.12.1
 */
public class A1_socket {
    /* 一 基础概念
        1.socket是什么 组成与作用
    简单来说就是一种IP地址与端口的结合描述协议；
    在网络传输中用于唯一标示两个端点(包括IP+Port)之间的链接
        2.TCP
    TCP是面向连接的通信协议，通过三次握手建立连接，通讯完成时要拆除连接，由于TCP是面向连接的所以只能用于端对端的通讯
    (同一个时刻一端只能连接另一个端口)
        3.UDP
    UDP是面向无连接的通信协议，UDP数据包括目的端口号和源端口号信息，由于通讯不需要连接所以可以实现广播发送,并不局限端对端
        二. demo
            socket 客户端demo 见 A1_socket_client
            socket 服务端demo 见 A1_socket_server
     */


}
