package io.grpc.examples.helloworld;

import IRC_service.Ircservice;
import IRC_service.Ircservice.gInt;
import IRC_service.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
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
        Ircservice.MessageFormat msg_temp = Ircservice.MessageFormat.newBuilder().setMsg(msg).setFrom(nickname).setToChannel(1, chaneList.get(1)).setMsgTime(tsTime).build();
//set to channel baru masukin 1 channel abis gatau caranya iterasi set trus ntar baru di build 
        return msg_temp;
    }
     
    private Ircservice.MessageFormat makeMessage(String msg, String chanel, String nickname) {

        long tsTime = getSecondNow();        
        Ircservice.MessageFormat msg_temp = Ircservice.MessageFormat.newBuilder().setMsg(msg).setFrom(nickname).setToChannel(1, chanel).setMsgTime(tsTime).build();

        return msg_temp;
    }

    @Override
    public void msgRecv(Ircservice.gVoid request, StreamObserver<Ircservice.gRepeatMsg> responseObserver) {
        List<Ircservice.MessageFormat> old_msg = IRCServer.msgList;
        List<Ircservice.MessageFormat> res_msg = new ArrayList<Ircservice.MessageFormat>();

        if (!old_msg.isEmpty()) {
            for (Ircservice.MessageFormat m : old_msg) {
                    res_msg.add(m);
            }
        }
        //gabisa return res msg ih kzl 
    //    return res_msg;            
    }

    @Override
    public void broadcastSend(Ircservice.gBroadcastSendParam request, StreamObserver<Ircservice.gVoid> responseObserver) {

        Ircservice.MessageFormat msg_temp = makeMessage(request.getMsg(), request.getChannelListList(), request.getUname());
        IRCServer.msgList.add(msg_temp);
    }

    @Override
    public void msgChannelSend(Ircservice.gChannelSendParam request, StreamObserver<Ircservice.gVoid> responseObserver) {
        List<String> channeList = new ArrayList<String>();
        channeList.add(request.getChannel());
        Ircservice.MessageFormat msg_temp = makeMessage(request.getMsg(), request.getChannel(),request.getUname());
        IRCServer.msgList.add(msg_temp);
    }

    @Override
    public void joinChannel(Ircservice.gString request, StreamObserver<gInt> responseObserver) {
            gInt reply = null;
            responseObserver.onValue(reply);
            responseObserver.onCompleted();
            
            IRCServer.channel_list.add(request.getValue());
            
    }
}
