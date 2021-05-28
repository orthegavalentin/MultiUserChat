package com.company;

import java.io.*;
import java.net.Socket;

public class ServerConnectionThread extends Thread {
    private Socket clientSocket;

    public ServerConnectionThread(Socket socket){
        this.clientSocket=socket;

    }
    @Override
    public void run() {
        super.run();
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void handleClientSocket() throws IOException, InterruptedException {
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ( (line = reader.readLine()) != null) {
            if ("quit".equalsIgnoreCase(line)) {
                break;
            }
            String msg = "You typed: " + line + "\n";
            outputStream.write(msg.getBytes());
        }

        clientSocket.close();
    }
}
