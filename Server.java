import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Time;
import java.util.Date;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    static Socket ServerClient;
    static ServerSocket welcomeSocket;
    static ReentrantLock syncLock = new ReentrantLock();

	public static void main(String[] args) throws Exception {

        // if (args.length != 2) {
        //     System.out.println("missing argument");
        //     System.exit(-1);
        // }

        //Hashtable for login attempt
        Hashtable<String, Integer> hashtable = new Hashtable<String , Integer>(); 

        //Assign Port no
        int serverPort = Integer.parseInt(args[0]);   
        
        //blocking time
        int block_time = Integer.parseInt(args[1]);

        
        //TCP Connection
        ServerSocket welcomeSocket = new ServerSocket(serverPort);
        System.out.println("Server is ready :");
        int counter = 0;

            while(true) {
                try {
                    // accept connection from connection queue
                    Socket connectionSocket = welcomeSocket.accept();
                    
                    //race condition // so we need to lock
                    syncLock.lock();
                    ServerClientThread us = new ServerClientThread(connectionSocket, counter, block_time,hashtable);
                    us.start();
                    counter++;
                    syncLock.unlock();

                } catch (SocketException e) {
                    System.out.println(e.toString());
                }
        
            }
    }

}