import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author Administrator
*/
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8001);
            System.out.println("Server is connecting....");
            Socket socket = serverSocket.accept();
            System.out.println("Server is connect");
            while (true) {
            	// To Create matrices
            	String clientInput1;
            	String clientInput2;
            	String clientInput3;
            	String clientInput4;
            	String clientInput;
            	String serverResult;
            	 //tao luong xu ly
                //DataInputStream  inFromClient = new DataInputStream(socket.getInputStream());
                //DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
                
            	BufferedReader inFromClient = new BufferedReader (new InputStreamReader(socket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
            	
                clientInput1 = inFromClient.readLine();
            	int rowsMatrixA = Integer.parseInt(clientInput1);

            	clientInput2 = inFromClient.readLine();
            	int colsMatrixA = Integer.parseInt(clientInput2);

            	clientInput3 = inFromClient.readLine();
            	int rowsMatrixB = Integer.parseInt(clientInput3);

            	clientInput4 = inFromClient.readLine();
            	int colsMatrixB = Integer.parseInt(clientInput4);
            	
            	if (colsMatrixA == rowsMatrixB)
            	{
	            	// To Create matrices
	            	double[][] matrixA = new double[rowsMatrixA][colsMatrixA];
	            	double[][] matrixB = new double[rowsMatrixB][colsMatrixB];
	            	double[][] matrixC = new double[rowsMatrixA][colsMatrixB];
	            	// Get input from client To fill Matrices
	            	// MatrixA
	            	for (int rowA=0;rowA<rowsMatrixA;rowA++) {
		            	for (int colA=0;colA<colsMatrixA;colA++){
		            	//System.out.println(“Insert Element Matrix A ” + “[” + rowA + “]” + “[” + colA + “]” + “: \n”);
		            	//System.out.println(‘rowA’ ‘colA’);
		            	clientInput = inFromClient.readLine();
		            	//outToServer.writeBytes(input + ‘\n’);
		            	matrixA[rowA][colA] = Integer.parseInt(clientInput);
		            	}
	            	}
	            	// MatrixB
	            	for (int rowB=0;rowB<rowsMatrixB;rowB++) {
		            	for (int colB=0;colB<colsMatrixB;colB++){
		            	//System.out.println(“Insert Element Matrix B ” + “[” + rowB + “]” + “[” + colB + “]” + “: \n”);
		            	clientInput = inFromClient.readLine();
		            	matrixB[rowB][colB] = Integer.parseInt(clientInput);
		            	}
	            	}
	            	// Multiplication C =A.B
	            	for(int rowA=0; rowA<rowsMatrixA; rowA++){
		            	for(int colB=0; colB<colsMatrixB; colB++){
			            	for(int element=0; element<colsMatrixA; element++){
			            		matrixC[rowA][colB] += matrixA[rowA][element]*matrixB[element][colB];
			            	}
		            	}
	            	}
	            	// To output matrixC (C = A.B)
	            	System.out.println("on Server’s Screen :" + '\n');
	            	System.out.println("Matrix C = AxB" + '\n');
	            	for(int rowC=0; rowC<rowsMatrixA; rowC++){
		            	for(int colC=0; colC<colsMatrixB; colC++){
		            	//System.out.print(matrixC[rowC][colC]+”\t”);
			            	serverResult = matrixC[rowC][colC]+"\t";
			            	outToClient.writeBytes(serverResult);
			            	System.out.print(serverResult);
		            	}
		            	System.out.println(" ");
		            	serverResult = ('\n' + " ");
		            	outToClient.writeBytes(serverResult);
	            	}
	            	//System.exit(0);
	            } //close if
	            else{
		            	System.out.println("on Server’s Screen : Wrong Matrix");
		            	serverResult = ("Wrong Matrix" + '\n');
		            	outToClient.writeBytes(serverResult);
            	}
            }
 
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}