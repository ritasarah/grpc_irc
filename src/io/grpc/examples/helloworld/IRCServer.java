package io.grpc.examples.helloworld;

import IRC_service.Ircservice;
import IRC_service.Ircservice.MessageFormat;
import IRC_service.UserServiceGrpc;

import io.grpc.ServerImpl;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class IRCServer {
  private static final Logger logger = Logger.getLogger(IRCServer.class.getName());

  /* The port on which the server should run */
  private int port = 50051;
  private ServerImpl server;
  
  public static List<MessageFormat> msgList;
  public static List<String> channel_list;

  private void start() throws Exception {
    server = NettyServerBuilder.forPort(port)
        .addService(UserServiceGrpc.bindService(new IRCHandler()))
        .build().start();
    logger.info("Server started, listening on " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        IRCServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }
    public IRCServer() {
        channel_list = new ArrayList<String>();
        msgList = new ArrayList<MessageFormat>();
    }
  /**
   * Main launches the server from the command line.
   */
  public static void main(String[] args) throws Exception {
    final IRCServer server = new IRCServer();
    server.start();
    
  }

    
}
