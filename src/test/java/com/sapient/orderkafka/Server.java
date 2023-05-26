package com.sapient.orderkafka;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket socketServer;

    Socket socket;

    DataInputStream dataInputStream;

    DataOutputStream dataOutputStream;

    Server(int port) throws IOException {
        socketServer = new ServerSocket(port);
        socket = socketServer.accept();
        dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void start(){
        new Thread(this::reading).start();
        new Thread(this::responding).start();
    }

    public void reading(){
        String s = "";
        while(!s.equalsIgnoreCase("Bye")){
            try {
                s = dataInputStream.readUTF();
                System.out.println("Client : "+s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void responding(){
        String s = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while(!s.equalsIgnoreCase("Bye")){
            try {
                dataOutputStream.writeBytes(bufferedReader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void stop(){
        try {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
            socketServer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                new Server(5000).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        //thread.join();
        new Thread(()-> {
            try {
                new Client("127.0.0.1",5000).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

}

class Client {

    Socket socket;

    DataInputStream dataInputStream;

    DataOutputStream dataOutputStream;

    Client(String host,int port) throws IOException {
        socket = new Socket(host, port);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void responding(){
        String s = "";
        DataInputStream dataInputStream1 = new DataInputStream(System.in);
        while(!s.equalsIgnoreCase("Bye")){
            try {
                dataOutputStream.writeUTF(dataInputStream1.readLine());
                dataInputStream1.read();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reading(){
        String s = "";
        while(!s.equalsIgnoreCase("Bye")){
            try {
                String s1 = dataInputStream.readUTF();
                System.out.println("Server : "+s1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void start() {
        new Thread(this::responding).start();
        new Thread(this::reading).start();
    }

}
