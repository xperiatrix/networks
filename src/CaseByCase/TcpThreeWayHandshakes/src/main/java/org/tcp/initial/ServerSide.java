package org.tcp.initial;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {

    public static InetSocketAddress localEndPoint = new InetSocketAddress("localhost", 9999);
//    public static InetSocketAddress remoteEndPoint = new InetSocketAddress("11.22.33.44", 9999);
    public static InetSocketAddress remoteEndPoint = new InetSocketAddress("localhost", 9999);

    public static void main(String[] args) {
        ServerSide server = new ServerSide();
        server.launch();
    }

    private void launch() {
        ServerSocket listenSocket = null;
        try {
            listenSocket = new ServerSocket();
            listenSocket.bind(localEndPoint, 5);

            int index = 0;
            while (true) {  // Server Never Dies.
               ++index;
                Socket connection = listenSocket.accept();
                if (connection == null) { continue; }

                connection.setKeepAlive(true);
                MyWorkerThread workThread = new MyWorkerThread();
                workThread.setIdentifier(index+"");
                workThread.setDataSocket(connection);
                workThread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
