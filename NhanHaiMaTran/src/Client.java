import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author Administrator
*/
public class Client {
	/*Ham nhap vao ma tran*/
	
    public static void main(String[] args) {
        try {
        	System.out.println("Client is Connecting....");
            Socket socket = new Socket("localhost", 8001);
            System.out.println("Client is Connect");
            while (true) {
            	String input1;
            	String input2;
            	String input3;
            	String input4;
            	String result;
            	//DataInputStream inFromUser  = new DataInputStream(socket.getInputStream());
            	BufferedReader inFromUser = new BufferedReader (new InputStreamReader (System.in));
            	DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
            	System.out.println( " Input Rows Matrix A :  \n" );
            	input1   =   inFromUser.readLine();             //get integer rowsMatrixA
            	outToServer.writeBytes(input1 + '\n');
            	int rowsMatrixA    =   Integer.parseInt( input1 );

            	System.out.println( " Input Cols Matrix A : \n" );
            	input2   =   inFromUser.readLine();             //get integer colsMatrixA
            	outToServer.writeBytes(input2 + '\n');
            	int colsMatrixA    =   Integer.parseInt( input2 );

            	System.out.println( " Input Rows Matrix B : \n" );
            	input3   =   inFromUser.readLine();             //get integer rowsMatrixB
            	outToServer.writeBytes(input3 + '\n');
            	int rowsMatrixB    =   Integer.parseInt( input3 );

            	System.out.println( " Input Cols Matrix B : \n" );
            	input4   =   inFromUser.readLine();             //get integer colsMatrixB
            	outToServer.writeBytes(input4 + '\n');
            	int colsMatrixB    =   Integer.parseInt( input4 );
            	
            	if (colsMatrixA == rowsMatrixB){
	            	// To fill Matrices
	            	// MatrixA
	            	for (int rowA=0;rowA<rowsMatrixA;rowA++) {
		            	for (int colA=0;colA<colsMatrixA;colA++){
			            	System.out.println("Insert Element Matrix A " + "[" + rowA + "]" + "[" + colA + "]" + ": \n");
			            	String input = inFromUser.readLine();
			            	outToServer.writeBytes(input + '\n');
		            	}
	            	}
	            	// MatrixB
	            	for (int rowB=0;rowB<rowsMatrixB;rowB++) {
		            	for (int colB=0;colB<colsMatrixB;colB++){
			            	System.out.println("Insert Element Matrix B " + "[" + rowB + "]" + "[" + colB + "]" + ": \n");
			            	String input = inFromUser.readLine();
			            	outToServer.writeBytes(input + '\n');
		            	}
	            	}
	            	// To output matrixC (C = A.B)
	            	System.out.println("on Client’s Screen :");
	            	System.out.println("Matrix C = AxB" + "\n");
	            	for(int rowC=0; rowC<rowsMatrixA; rowC++){
		            	for(int colC=0; colC<colsMatrixB; colC++){
		            		result = inFromServer.readLine();
			            	System.out.println(result);
		            	}
		            	result = inFromServer.readLine();
		            	System.out.println(result);
	            	}
	            	socket.close();
            	} //close if
            	else{
	            	System.out.println("on Client’s Screen :");
	            	result = inFromServer.readLine();
	            	System.out.println(result);
            	}
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

