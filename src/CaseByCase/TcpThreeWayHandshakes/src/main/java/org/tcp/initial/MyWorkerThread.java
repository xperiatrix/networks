package org.tcp.initial;

import java.io.*;
import java.net.Socket;

public class MyWorkerThread extends Thread {

    private Socket dataSocket = null;
    private String identifier = "";

    @Override
    public void run() {
        super.run();
        if (dataSocket == null) { return; }

        oneConnectionOnPerThread();
    }

    private final void oneConnectionOnPerThread() {
        InputStream inputStream = null;
        BufferedReader reader = null;
        OutputStream outputStream = null;
        PrintWriter writer = null;

        while (dataSocket.isConnected()) {
            try {
                inputStream = dataSocket.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String requestMsg = reader.readLine();
                System.out.println(requestMsg);

                outputStream = dataSocket.getOutputStream();
                writer = new PrintWriter(new OutputStreamWriter(outputStream));
                // Using `stdio` that means `readLine()` won't stop reading from io-stream until `\n`
                writer.write("This is the server-response to clientId-" + identifier + "\n");
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            if (writer != null) { writer.close(); }
            if (outputStream != null) { outputStream.close(); }
            if (reader != null) { reader.close(); }
            if (inputStream != null) { inputStream.close(); }
            dataSocket.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Socket getDataSocket() {
        return dataSocket;
    }

    public void setDataSocket(Socket dataSocket) {
        this.dataSocket = dataSocket;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
