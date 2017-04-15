import java.io.*;
import java.net.*;
 
public class ClientDemo {
   public static void main(String[] args) {
 
       // Dia chi may chu.
       final String serverHost = "localhost";
 
       Socket socketOfClient = null;
       BufferedWriter os = null;
       BufferedReader is = null;
 
       try {
           // Gui yeu cau ket noi toi Server dang lang nghe
    	   // tren may 'localhost' cong 7777.
           socketOfClient = new Socket(serverHost, 7777);
 
           // Tao luong dau ra tai client (Gui du lieu toi server)
           os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
 
           // Luong dau vao tai Client (Nhan du lieu tu server).
           is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
 
       } catch (UnknownHostException e) {
           System.err.println("Don't know about host " + serverHost);
           return;
       } catch (IOException e) {
           System.err.println("Couldn't get I/O for the connection to " + serverHost);
           return;
       }
 
       try {
		   // Ghi du lieu vao luung dau ra cua Socket tai Client.
		   // Doc du lieu tra loi tu phia server
		   // Bang cach doc luong dau vao cua Socket tai Client.
    	   //tao luong nhap du lieu tu ban phim
    	   DataInputStream inFromUser = new DataInputStream(System.in);
           String responseLine;
           while ((responseLine = is.readLine()) != null) {
        	   System.out.println(responseLine);
        	   if (responseLine.indexOf("OK") != -1) {
                   break;
               }
        	   System.out.print(">> Chat to room: ");
        	   String textchat=inFromUser.readLine();
        	   os.write(textchat);
        	   os.newLine();
               os.flush();
           }
           os.close();
           is.close();
           socketOfClient.close();
       } catch (UnknownHostException e) {
           System.err.println("Trying to connect to unknown host: " + e);
       } catch (IOException e) {
           System.err.println("IOException:  " + e);
       }
   }
 
}