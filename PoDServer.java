/**
* I declare that this assignment is my own work and that all 
* material previously written or published in any source by any 
* other person has been duly acknowledged in the assignment. I 
* have not submitted this work, or a significant part thereof, 
* previously as part of any academic program. In submitting this 
* assignment I give permission to copy it for assessment purposes 
* only.
* 
* title: PoDServer.java
* description: A Poem of the day server. 
* @author craigleech
* @version 1.0
* @since 2018-06-11
*/

/**
 * DOCUMENTATION...
 */

/**                                                                               
 *
 *<P>
 * This program is a simple Poem of the day server to be used with a telnet client.  Server is started via the command
 * prompt. Client will be prompted with welcome message and instructions on how to retrieved poems from the server.
 * User will select a poem by responding with a number representing what type of poem they would like.
 * Server will write desired poem to client then terminates the session immediately. 
 *</P>
 *<P>
 * This program uses Java(TM) SE Runtime Environment (build 9.0.4+11)
 *</P>
 *                                                                              
 *<DL>
 *<DT> Compiling and running instructions</DT>
 *<DT> Change to the directory containing the source code.</DT>
 *<DT> Move poems.txt into directory containing source code</DT>
 *<DD> Compile:    javac PoDServer.java</DD>
 *<DD> Run:        java PoDServer</DD>
 *<DD> To view with telnet client: telnet localhost 12900</DD>
 *<DD> Document:   javadoc PoDServer.java</DD>
 *</DL>
 */

/**
 *
 * <H3>Test Plan</H3>
 *
 *<P>
 * 1. i) Navigate to directory containg source code.
 *    ii) Ensure poems.txt is inside directory containing source code.
 *    iii) Start the Server from command prompt.
 *    iv)  Start telnet client from and connect via: telnet localhost 12900
 *    v) Inside Telnet client enter the following: 1
 *    vi) Example output of PoDServer Serving a poem to telnet client:
 *      
 *      *** Note - User will first be prompted with welcome message and usage instructions
 *      Output:
 *      1) Roses are red, here's something new, violets are violet, not f***ing blue.
 *
 * EXPECTED:
 *   Server will start as expected
 *</P>
 *<P>
 * 2. Good data cases:
 * EXPECTED:
 *    Start the server from command prompt with the following parameters:
 *
 *   1. java PoDServer
 *      
 *   2. In telnet client respond with one of the following options: 1, 2, or 3.
 *   3. Poem will be written to the client:
 *
 *      1) Roses are red, here's somthing new, violets are violet, not f***ing blue.
 *
 * ACTUAL:
 *    Results displayed as expected.
 *</P>
 *<P>
 * 3. Bad data cases:
 * EXPECTED:
 *    Run the following test cases by running the program with the following parameters:
 *
 *    1.  (Unable to find poems.txt) to run: java PoDServer
 *      output should be:
 *      
 *      Waiting for a  connection...
 *      Received from  client: 1
 *      Unable to open poems.txt
 *
 * ACTUAL: 
 *    Results displayed as expected.
 *</P>
 */ 


/**** Import java classes ****/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PoDServer {
  public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(12900, 100,
        InetAddress.getByName("localhost"));
    System.out.println("Server started  at:  " + serverSocket);

    while (true) {
      System.out.println("Waiting for a  connection...");

      final Socket activeSocket = serverSocket.accept();

      System.out.println("Received a  connection from  " + activeSocket);
      Runnable runnable = () -> handleClientRequest(activeSocket);
      new Thread(runnable).start(); // start a new thread
    }
  }

  public static void handleClientRequest(Socket socket) {
    try{
      // create the BufferedReader and BufferedWriter
      BufferedReader socketReader = null;
      BufferedWriter socketWriter = null;
      socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      // write the welcome message to the client
      socketWriter.write("Welcome to the PoD Server!, Please select which type poem you would like. ");
      socketWriter.write("\n");// new line
      socketWriter.flush(); // flush socket writer every time...
      socketWriter.write("To retrieve a poem type in the number associated with the type of the poem.");
      socketWriter.write("\n");
      socketWriter.flush();
      socketWriter.write("The following poems are available: ");
      socketWriter.write("\n");
      socketWriter.flush();
      socketWriter.write("1. Roses are Red");
      socketWriter.write("\n");
      socketWriter.flush();   
      socketWriter.write("2. Haiku");
      socketWriter.write("\n");
      socketWriter.flush();    
      // declare a string inMsg
      String inMsg = null;
      // Set inMsg = readline from buffered reader
        inMsg = socketReader.readLine();
      // Print message showing connection to client
        System.out.println("Received from  client: " + inMsg);
      // set the poems.txt file
        File file = new File("poems.txt");
      // try to read from file line by line with scanner
        try {
            // declare a scanner that reads file
            Scanner scanner = new Scanner(file);
            /// while there are still lines to read..
            while (scanner.hasNextLine()) {
              // line = next line of scanner
                String line = scanner.nextLine();
              // if line containes the inMsg, print the whole line to the client  
                if(line.contains(inMsg)) { 
                  socketWriter.flush(); 
                  socketWriter.write(line);
                  socketWriter.write("\n");
                  socketWriter.flush(); 
                }
            }
        // catch file not found exception, and print to terminal
          } catch(FileNotFoundException e) { 
              System.out.println("Unable to open poems.txt");
          }
        // close the socket
          socket.close();
        }
    // catch exception and print stack trace
    catch(Exception e){
      e.printStackTrace();
    }
  }
}