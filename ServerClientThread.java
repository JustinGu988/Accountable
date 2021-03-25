import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.locks.ReentrantLock;

public class ServerClientThread extends Thread {
    private Socket connectionSocket;
    private int clientNo;
    private Hashtable<String, Integer> hashtable;
    private int block_time;
    private String login_User;
    static ReentrantLock syncLock = new ReentrantLock();
    static int UPDATE_INTERVAL = 1000;//milliseconds
    private String temp_log;

    public ServerClientThread(Socket socket,int counter ,int block_time,Hashtable<String, Integer> hashtable) {
        this.connectionSocket = socket;
        this.clientNo=counter;
        this.block_time = block_time;
        this.hashtable = hashtable;
    }

    public void add_to_hash(Hashtable<String, Integer> hashtable , String UserID) {
        
        //we will increase the val of hashtable
        if (hashtable.containsKey(UserID)) {
            int cur = hashtable.get(UserID);

            if (cur >= 3) {
                cur = 3;
            } else if (cur != -1) {
                cur = cur + 1;
            }

            hashtable.put(UserID,cur);
        } else {
            hashtable.put(UserID,1);
        }

    }

    public void run() {
        //syncLock.lock();
        try {
            //System.out.println("thread " + clientNo + " is running");
            // create read stream to get input
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            //obtain info from client
            String UserID = inFromClient.readLine();            
            String password = inFromClient.readLine();
                            
            //check with  credentials.txt
            int authentication = 0;
            File file = new File("credentials.txt");
            Scanner FileReader = new Scanner(file);

            // //add the count to hash table
            // add_to_hash(hashtable,UserID);

            //splitting the string

            //return output based on authentication
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());            
            

            

            outToClient.close();
            inFromClient.close();
            connectionSocket.close();


        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (SocketException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }


        syncLock.unlock();

        //sleep for UPDATE_INTERVAL
        
        try{
            Thread.sleep(UPDATE_INTERVAL);//in milliseconds
        } catch (InterruptedException e){
            System.out.println(e);
        }
        

    }
}
 
