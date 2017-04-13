import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
 
public class ServerProgram {
 
   public static void main(String args[]) throws IOException {
 
       ServerSocket listener = null;
 
       System.out.println("Server is waiting to accept user...");
       int clientNumber = 0;
       // Mo mot ServerSocket tai cong 7777.
       // Chu y ban khong the chon cong nho hon 1023 neu khong la nguoi dung
       // dac quyen (privileged users (root)).
       try {
           listener = new ServerSocket(7777);
       } catch (IOException e) {
           System.out.println(e);
           System.exit(1);
       }
       try {
           while (true) {
 
               // Chap nhan mot yeu cau ket noi tu phia Client.
               // Dong thoi nhan duoc mot doi tuong Socket toi server.
 
               Socket socketOfServer = listener.accept();
               new ServiceThread(socketOfServer, clientNumber++).start();
           }
       } finally {
           listener.close();
       }
 
   }
 
   private static void log(String message) {
       System.out.println(message);
   }
 
   private static class ServiceThread extends Thread {
 
       private int clientNumber;
       private Socket socketOfServer;
 
       public ServiceThread(Socket socketOfServer, int clientNumber) {
           this.clientNumber = clientNumber;
           this.socketOfServer = socketOfServer;
 
           // Log
           log("New connection with client# " + this.clientNumber + " at " + socketOfServer);
           // Mo luong vao ra tren Socket tai Server.
           try {
			BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
			os.write("Xin Chao user: "+ clientNumber+" ok de ket thuc\n");
            os.newLine(); // ket thúc dong
            os.flush();  // day du lieu đi.
		} catch (IOException e) {
			System.out.println(e);
            e.printStackTrace();
		}
       }
 
       @Override
       public void run() {
 
           try {
 
    
               // Mo luong vao ra tren Socket tai Server.
               BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
               BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
               
               while (true) {
            	   // Doc du lieu tai server (Do client gui toi).
                   String line = is.readLine();
                   // Ghi vao luong dau ra cua Socket tai Server.
                   // (Nghia la gui tai Client).
                   os.write("User "+clientNumber+" type : " + line);
                   // Ket thuc dong
                   os.newLine();
                   // Day da lieu di
                   os.flush();
                   System.out.println("User "+clientNumber+" type : "+line);
                   // Neu nguoi dung gui toi QUIT (Muon ket thuc tro chuyen).
                   if (line.equals("QUIT")) {
                       os.write(">> OK");
                       os.newLine();
                       os.flush();
                       break;
                   }
               }
 
           } catch (IOException e) {
               System.out.println(e);
               e.printStackTrace();
           }
       }
   }
}