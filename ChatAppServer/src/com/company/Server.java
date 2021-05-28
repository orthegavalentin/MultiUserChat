package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int port = 1234;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true) {
                System.out.println("About to accept client connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                ServerConnectionThread client = new ServerConnectionThread(clientSocket);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
