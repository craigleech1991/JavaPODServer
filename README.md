# JavaPODServer
A Poem of the Day Server Java



  This program is a simple Poem of the day server to be used with a telnet client.  Server is started via the command
  prompt. Client will be prompted with welcome message and instructions on how to retrieved poems from the server.
  User will select a poem by responding with a number representing what type of poem they would like.
  Server will write desired poem to client then terminates the session immediately. 

 * This program uses Java(TM) SE Runtime Environment (build 9.0.4+11)

  Compiling and running instructions
  Change to the directory containing the source code.
  Move poems.txt into directory containing source code.
  Compile:    javac PoDServer.java
  Run:        java PoDServer
  To view with telnet client: telnet localhost 12900
  Document:   javadoc PoDServer.java
