package io.grpc.examples.helloworld;

import IRC_service.Ircservice;
import IRC_service.Ircservice.MessageFormat;
import IRC_service.Ircservice.gInt;
import IRC_service.Ircservice.gRepeatMsg;
import IRC_service.Ircservice.gVoid;
import IRC_service.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import com.google.protobuf.LazyStringArrayList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rita
 */
public class IRCHandler implements UserServiceGrpc.UserService {

    private long getSecondNow(){
        java.util.Date today = new java.util.Date();
        java.sql.Timestamp ts_now = new java.sql.Timestamp(today.getTime());
        long tsTime = ts_now.getTime();
        return tsTime;
    }

    private Ircservice.MessageFormat makeMessage(String msg, List<String> chaneList, String nickname) {

        long tsTime = getSecondNow();        
        com.google.protobuf.LazyStringList LazyList = new com.google.protobuf.LazyStringArrayList();
        for (int i=0; i<chaneList.size(); i++)
            LazyList.add(chaneList.get(i));
        
//        Ircservice.MessageFormat msg_temp = Ircservice.MessageFormat.newBuilder().setMsg(msg).setFrom(nickname).setToChannel(0, chaneList.get(1)).setMsgTime(tsTime).build();
        Ircservice.MessageFormat msg_temp = Ircservice.MessageFormat.newBuilder().setMsg(msg).setFrom(nickname).addAllToChannel(LazyList).setMsgTime(tsTime).build();

        return msg_temp;
    }
     
    private Ircservice.MessageFormat makeMessage(String msg, String chanel, String nickname) {

        long tsTime = getSecondNow();        
        com.google.protobuf.LazyStringList LazyList = new com.google.protobuf.LazyStringArrayList();
        LazyList.add(chanel);
        
//        Ircservice.MessageFormat msg_temp = Ircservice.MessageFormat.newBuilder().setMsg(msg).setFrom(nickname).setToChannel(0, chanel).setMsgTime(tsTime).build();
        Ircservice.MessageFormat msg_temp = Ircservice.MessageFormat.newBuilder().setMsg(msg).setFrom(nickname).addAllToChannel(LazyList).setMsgTime(tsTime).build();

        return msg_temp;
    }

    @Override
    public void msgRecv(Ircservice.gVoid request, StreamObserver<Ircservice.gRepeatMsg> responseObserver) {
        List<Ircservice.MessageFormat> old_msg = IRCServer.msgList;
        List<Ircservice.MessageFormat> res_msg = new ArrayList<Ircservice.MessageFormat>();
        
//        System.err.println("Receiving message");
        if (!old_msg.isEmpty()) {
            for (Ircservice.MessageFormat m : old_msg) {
                    res_msg.add(m);
            }
            gRepeatMsg rptmsg = gRepeatMsg.newBuilder().addAllMsgs(res_msg).build();
            responseObserver.onValue(rptmsg);
        }
        
//        System.out.println(res_msg.toString());
//        System.err.println("Finished receiving message");
        
        responseObserver.onCompleted();

    }

    @Override
    public void broadcastSend(Ircservice.gBroadcastSendParam request, StreamObserver<Ircservice.gVoid> responseObserver) {
//        System.out.println("broadcast send ");
        Ircservice.MessageFormat msg_temp = makeMessage(request.getMsg(), request.getChannelListList(), request.getUname());
        IRCServer.msgList.add(msg_temp);
        
        responseObserver.onValue(gVoid.getDefaultInstance());
        responseObserver.onCompleted();
//        System.out.println("Broadcast message "+ request.getMsg().toString());
    }

    @Override
    public void msgChannelSend(Ircservice.gChannelSendParam request, StreamObserver<Ircservice.gVoid> responseObserver) {
//        System.err.println("Msg channel send");
        List<String> channeList = new ArrayList<String>();
        channeList.add(request.getChannel());
        Ircservice.MessageFormat msg_temp = makeMessage(request.getMsg(), request.getChannel(),request.getUname());
        IRCServer.msgList.add(msg_temp);
        
        responseObserver.onValue(gVoid.getDefaultInstance());
        responseObserver.onCompleted();

//        System.err.println("Handler : sending message-> "+request.getMsg().toString()+" to ->"+request.getChannel().toString());

    }

    @Override
    public void joinChannel(Ircservice.gString request, StreamObserver<gInt> responseObserver) {
//        System.err.println("Handler : Joining");
        gInt reply = gInt.newBuilder().setValue(1).build();
        System.err.println(request.getValue());
        IRCServer.channel_list.add(request.getValue());

        responseObserver.onValue(reply);
        responseObserver.onCompleted();

//        System.err.println("Handler : join channel "+request.getValue().toString());
    }
}
