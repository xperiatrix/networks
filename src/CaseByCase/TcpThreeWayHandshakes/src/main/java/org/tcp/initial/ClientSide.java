package org.tcp.initial;

import java.io.*;
import java.net.Socket;

public class ClientSide {

    public static void main(String[] args) {
        invokeTenRequests();
    }

    private static void invokeTenRequests() {
        int number = 1;
        for (int i=0; i<10; i++) {
            number++;
            int finalNumber = number;
            Thread clientThread = new Thread(() -> {
                ClientSide client = new ClientSide();
                client.requestAt(finalNumber);
            });
            clientThread.start();
        }
    }

    private void requestAt(int index) {
        Socket socket = null;
        OutputStream outputStream = null;
        PrintWriter writer = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            socket = new Socket();
            // socket.connect(ServerSide.localEndPoint, 30);
            socket.connect(ServerSide.remoteEndPoint, 30);
            while (socket.isConnected()) {
                outputStream = socket.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream));
                // Using `stdio` that means `readLine()` won't stop reading from io-stream until `\n`
                writer.write("Say hello from clientId-" + index + "\n");
                writer.flush();

                inputStream = socket.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String responseMsg = reader.readLine();
                System.out.println(responseMsg);
            }

            if (writer != null) { writer.close(); }
            if (outputStream != null) { outputStream.close(); }
            if (reader != null) { reader.close(); }
            if (inputStream != null) { inputStream.close(); }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
