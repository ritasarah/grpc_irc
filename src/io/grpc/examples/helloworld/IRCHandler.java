/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.grpc.examples.helloworld;

import IRC_service.Ircservice;
import IRC_service.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class IRCHandler {
    private static class ServiceImpl implements UserServiceGrpc.UserService{

        public ServiceImpl() {
            
        }
        
        private long getSecondNow(){
            java.util.Date today = new java.util.Date();
            java.sql.Timestamp ts_now = new java.sql.Timestamp(today.getTime());
            long tsTime = ts_now.getTime();
            return tsTime;
        }
            
        private Ircservice.MessageFormat makeMessage(String msg, List<String> chaneList, String nickname) {

            long tsTime = getSecondNow();        
            Ircservice.MessageFormat msg_temp = Ircservice.MessageFormat.newBuilder().setMsg(msg).setFrom(nickname).setToChannel(1, chaneList.get(1)).setMsgTime(tsTime).build();

            return msg_temp;
        }

        @Override
        public StreamObserver<Ircservice.gString> joinChannel(StreamObserver<Ircservice.gInt> responseObserver) {
            
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //    return res_msg;            
        }

        @Override
        public void broadcastSend(Ircservice.gBroadcastSendParam request, StreamObserver<Ircservice.gVoid> responseObserver) {
            
            
          //  Ircservice.MessageFormat msg_temp = makeMessage(msg, channelList, uname);
          //  IRCServer.msgList.add(msg_temp);
        }

        @Override
        public void msgChannelSend(Ircservice.gChannelSendParam request, StreamObserver<Ircservice.gVoid> responseObserver) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
