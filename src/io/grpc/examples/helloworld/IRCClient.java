package io.grpc.examples.helloworld;

import IRC_service.Ircservice;
import IRC_service.Ircservice.gString;
import IRC_service.UserServiceGrpc;

import io.grpc.ChannelImpl;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class IRCClient {
  private static final Logger logger = Logger.getLogger(IRCClient.class.getName());

  private final ChannelImpl channel;
  private final UserServiceGrpc.UserServiceBlockingStub blockingStub;
//  private final GreeterGrpc.GreeterBlockingStub blockingStub;

  /** Construct client connecting to HelloWorld server at {@code host:port}. */
  public IRCClient(String host, int port) {
    channel =
        NettyChannelBuilder.forAddress(host, port).negotiationType(NegotiationType.PLAINTEXT)
            .build();
    blockingStub = UserServiceGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  /** Say hello to server. */
  public void greet(String name) {
    try {
      logger.info("Will try to greet " + name + " ...");
      gString request = gString.newBuilder().setValue(name).build();
     // gString response = blockingStub.sayHello(request);
     // logger.info("Greeting: " + response.getMessage());
    } catch (RuntimeException e) {
      logger.log(Level.WARNING, "RPC failed", e);
      return;
    }
  }

  /**
   * Greet server. If provided, the first element of {@code args} is the name to use in the
   * greeting.
   */
  public static void main(String[] args) throws Exception {
    IRCClient client = new IRCClient("localhost", 50051);
    try {
      /* Access a service running on the local machine on port 50051 */
      String user = "test";
      if (args.length > 0) {
        user = args[0]; /* Use the arg as the name to greet if provided */
      }
      client.greet(user);
    } finally {
      client.shutdown();
    }
  }
}
