package clientClasses;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {

            socket = new Socket("localhost", 1234);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedReader = new BufferedReader(inputStreamReader);
            Scanner scanner = new Scanner(System.in);
            while (true) {


                String msgTosend = scanner.nextLine();
                bufferedWriter.write(msgTosend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println("received message " + bufferedReader.readLine());
                if (msgTosend.equals("quit"))
                    break;

            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {


                e.printStackTrace();
            }
        }


    }
}