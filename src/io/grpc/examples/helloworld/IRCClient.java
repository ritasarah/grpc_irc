package io.grpc.examples.helloworld;

import IRC_service.Ircservice;
import IRC_service.Ircservice.MessageFormat;
import IRC_service.Ircservice.gBroadcastSendParam;
import IRC_service.Ircservice.gChannelSendParam;
import IRC_service.Ircservice.gInt;
import IRC_service.Ircservice.gRepeatMsg;
import IRC_service.Ircservice.gString;
import IRC_service.Ircservice.gVoid;
import IRC_service.UserServiceGrpc;

import io.grpc.ChannelImpl;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
    
  static String nickname;
  static List<String> channel_list;
  static long lastFetch;

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
//   try{      
      channel_list = new ArrayList<String>();
      
      Runnable listen = () -> {
//        System.err.println("ini thread listener");
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(IRCClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<MessageFormat> msg_list = client.msgRecv();
//            System.err.println("msg list size : "+msg_list.size());
            if (!msg_list.isEmpty()) {
//                System.err.println("msg list tidak empty");
                String res = "";
                for (MessageFormat m : msg_list) {
                    if (m.getMsgTime() > lastFetch) {
                        for (String s1 : m.getToChannelList()) {
                            if (channel_list.contains(s1)) {
                                res = "[" + s1 + "] (" + m.getFrom() + ") " + m.getMsg();
                                System.out.println(res);
                            }
                        }
                    }
                }
            }
            lastFetch = getSecondNow();
        }
    };
    new Thread(listen).start();
      
                        
//    Runnable main_thread = new Runnable() {
//        @Override
//        public void run() {
            Scanner in = new Scanner(System.in);
            String s;
            String mode = "", channel = "", msg = "";
            generateUname();
            Scanner input = new Scanner (System.in);

            // Run mode
            do {
                mode = input.next();

                if (mode.equals("/NICK")) { // Set nickname's client
                    nickname = input.next();
                    System.out.println("Ganti nickname berhasil!");
                }

                else if (mode.equals("/JOIN")) { // Join channel X
                    channel = input.next();
                    try{
                        client.joinChannel(channel);
                        if(!(channel_list.contains(channel))){
                        channel_list.add(channel);}
                       System.out.println("Join pada channel " + channel + " berhasil!");
                    }catch(Exception e) {
                        System.err.println ("Join pada channel gagal");
                    }                    
                }
                else if (mode.equals("/LEAVE")) { // Leave channel X
                    channel = input.next();
                    channel_list.remove(channel);
                    System.out.println("Leave pada channel " + channel + " berhasil!");
                }

                else if (mode.equals("/EXIT")) { // Exit 
                    System.out.println("Exiting program...");
                    System.exit(0);
                }

                else {
                    if (mode.charAt(0) == '@') { // Message channel X
                        channel = mode.substring(1, mode.length());
                        msg = input.nextLine();
                        client.msgChannelSend(msg, channel, nickname);
                    }
                    else { // Message to all channel
                        msg = mode + " " + input.nextLine();
                        client.broadcastSend(msg, nickname, channel_list);
                    }
                }
            } while (true);
            
            
//        }
//    };
//    new Thread(main_thread).start();
//


//    } finally {
//      client.shutdown();
//    }
  }
  
      private static long getSecondNow(){
        java.util.Date today = new java.util.Date();
        java.sql.Timestamp ts_now = new java.sql.Timestamp(today.getTime());
        long tsTime = ts_now.getTime();
        return tsTime;
    }
    
     public static void generateUname(){
	String usernames[] = {"Ludger","Elle","Jude","Milla","Alvin","Rowen","Elize","Leia"};
	String uname;
        Random rand = new Random();
	
	uname = usernames[(int)(rand.nextInt(usernames.length))] + (int) rand.nextInt(50) + 1;
	System.out.println("Username: " + uname);
	
	nickname = uname;
    }

    private List<MessageFormat> msgRecv() {
        List<MessageFormat> res = new ArrayList<MessageFormat>();

        try {
            gRepeatMsg listmsg = blockingStub.msgRecv(gVoid.getDefaultInstance());
//            System.out.println(listmsg.toString());
            if(listmsg.getMsgsCount()!= 0){
                for (int i=0 ; i<listmsg.getMsgsCount();i++)
                    res.add(listmsg.getMsgs(i));
            }else{
                MessageFormat blank = MessageFormat.newBuilder().setMsg("").setFrom("").build();
                res.add(blank);
            }
            
        } catch (RuntimeException e) {
//          logger.log(Level.WARNING, "RPC failed", e);
        }
        //harusnya return list message tapi bingung ah pundung 
      return res;

    }

    private void joinChannel(String channel) {
        try {
          gString request = gString.newBuilder().setValue(channel).build();
//          System.err.println("Request to join " + request.toString()); 
          gInt status = blockingStub.joinChannel(request);
//          System.err.println("Status " +status.toString());

        } catch (RuntimeException e) {
          logger.log(Level.WARNING, "RPC failed", e);
        }
    }

    private void msgChannelSend(String msg, String channel, String nickname) {
        try {
          gChannelSendParam request = gChannelSendParam.newBuilder().setMsg(msg).setChannel(channel).setUname(nickname).build();
          blockingStub.msgChannelSend(request);

        } catch (RuntimeException e) {
          logger.log(Level.WARNING, "RPC failed", e);
        }    
    }

    private void broadcastSend(String msg, String nickname, List<String> channel_list) {
        try {
          gBroadcastSendParam request = gBroadcastSendParam.newBuilder().setMsg(msg).setUname(nickname).addAllChannelList(channel_list).build();
          blockingStub.broadcastSend(request);

        } catch (RuntimeException e) {
          logger.log(Level.WARNING, "RPC failed", e);
        }    
    }
     
}
