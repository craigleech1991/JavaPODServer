# JavaPODServer
A Poem of the Day Server Java



  This program is a simple Poem of the day server to be used with a telnet client.  Server is started via the command
  prompt. Client will be prompted with welcome message and instructions on how to retrieved poems from the server.
  User will select a poem by responding with a number representing what type of poem they would like.
  Server will write desired poem to client then terminates the session immediately. 

 * This program uses Java(TM) SE Runtime Environment (build 9.0.4+11)

  Compiling and running instructions
1) Navigate to Directory containing source code.
2) Move poems.txt into directory containing source code.
3) Compile: javac PoDServer.java
4) Run: Java PoDServer
5) To view with telnet client: telnet (host) (port)
6) Documentation: javadoc PoDserver.java
