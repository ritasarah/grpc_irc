package IRC_service;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class UserServiceGrpc {

  // Static method descriptors that strictly reflect the proto.
  public static final io.grpc.MethodDescriptor<IRC_service.Ircservice.gString,
      IRC_service.Ircservice.gInt> METHOD_JOIN_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING,
          "IRC_service.UserService", "joinChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(IRC_service.Ircservice.gString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(IRC_service.Ircservice.gInt.parser()));
  public static final io.grpc.MethodDescriptor<IRC_service.Ircservice.gVoid,
      IRC_service.Ircservice.gRepeatMsg> METHOD_MSG_RECV =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "IRC_service.UserService", "msgRecv",
          io.grpc.protobuf.ProtoUtils.marshaller(IRC_service.Ircservice.gVoid.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(IRC_service.Ircservice.gRepeatMsg.parser()));
  public static final io.grpc.MethodDescriptor<IRC_service.Ircservice.gBroadcastSendParam,
      IRC_service.Ircservice.gVoid> METHOD_BROADCAST_SEND =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "IRC_service.UserService", "broadcastSend",
          io.grpc.protobuf.ProtoUtils.marshaller(IRC_service.Ircservice.gBroadcastSendParam.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(IRC_service.Ircservice.gVoid.parser()));
  public static final io.grpc.MethodDescriptor<IRC_service.Ircservice.gChannelSendParam,
      IRC_service.Ircservice.gVoid> METHOD_MSG_CHANNEL_SEND =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "IRC_service.UserService", "msgChannelSend",
          io.grpc.protobuf.ProtoUtils.marshaller(IRC_service.Ircservice.gChannelSendParam.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(IRC_service.Ircservice.gVoid.parser()));

  public static UserServiceStub newStub(io.grpc.Channel channel) {
    return new UserServiceStub(channel);
  }

  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserServiceBlockingStub(channel);
  }

  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserServiceFutureStub(channel);
  }

  public static interface UserService {

    public io.grpc.stub.StreamObserver<IRC_service.Ircservice.gString> joinChannel(
        io.grpc.stub.StreamObserver<IRC_service.Ircservice.gInt> responseObserver);

    public void msgRecv(IRC_service.Ircservice.gVoid request,
        io.grpc.stub.StreamObserver<IRC_service.Ircservice.gRepeatMsg> responseObserver);

    public void broadcastSend(IRC_service.Ircservice.gBroadcastSendParam request,
        io.grpc.stub.StreamObserver<IRC_service.Ircservice.gVoid> responseObserver);

    public void msgChannelSend(IRC_service.Ircservice.gChannelSendParam request,
        io.grpc.stub.StreamObserver<IRC_service.Ircservice.gVoid> responseObserver);
  }

  public static interface UserServiceBlockingClient {

    public IRC_service.Ircservice.gRepeatMsg msgRecv(IRC_service.Ircservice.gVoid request);

    public IRC_service.Ircservice.gVoid broadcastSend(IRC_service.Ircservice.gBroadcastSendParam request);

    public IRC_service.Ircservice.gVoid msgChannelSend(IRC_service.Ircservice.gChannelSendParam request);
  }

  public static interface UserServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<IRC_service.Ircservice.gRepeatMsg> msgRecv(
        IRC_service.Ircservice.gVoid request);

    public com.google.common.util.concurrent.ListenableFuture<IRC_service.Ircservice.gVoid> broadcastSend(
        IRC_service.Ircservice.gBroadcastSendParam request);

    public com.google.common.util.concurrent.ListenableFuture<IRC_service.Ircservice.gVoid> msgChannelSend(
        IRC_service.Ircservice.gChannelSendParam request);
  }

  public static class UserServiceStub extends io.grpc.stub.AbstractStub<UserServiceStub>
      implements UserService {
    private UserServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public io.grpc.stub.StreamObserver<IRC_service.Ircservice.gString> joinChannel(
        io.grpc.stub.StreamObserver<IRC_service.Ircservice.gInt> responseObserver) {
      return asyncClientStreamingCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), responseObserver);
    }

    @java.lang.Override
    public void msgRecv(IRC_service.Ircservice.gVoid request,
        io.grpc.stub.StreamObserver<IRC_service.Ircservice.gRepeatMsg> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_MSG_RECV, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void broadcastSend(IRC_service.Ircservice.gBroadcastSendParam request,
        io.grpc.stub.StreamObserver<IRC_service.Ircservice.gVoid> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_BROADCAST_SEND, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void msgChannelSend(IRC_service.Ircservice.gChannelSendParam request,
        io.grpc.stub.StreamObserver<IRC_service.Ircservice.gVoid> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_MSG_CHANNEL_SEND, callOptions), request, responseObserver);
    }
  }

  public static class UserServiceBlockingStub extends io.grpc.stub.AbstractStub<UserServiceBlockingStub>
      implements UserServiceBlockingClient {
    private UserServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public IRC_service.Ircservice.gRepeatMsg msgRecv(IRC_service.Ircservice.gVoid request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_MSG_RECV, callOptions), request);
    }

    @java.lang.Override
    public IRC_service.Ircservice.gVoid broadcastSend(IRC_service.Ircservice.gBroadcastSendParam request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_BROADCAST_SEND, callOptions), request);
    }

    @java.lang.Override
    public IRC_service.Ircservice.gVoid msgChannelSend(IRC_service.Ircservice.gChannelSendParam request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_MSG_CHANNEL_SEND, callOptions), request);
    }
  }

  public static class UserServiceFutureStub extends io.grpc.stub.AbstractStub<UserServiceFutureStub>
      implements UserServiceFutureClient {
    private UserServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<IRC_service.Ircservice.gRepeatMsg> msgRecv(
        IRC_service.Ircservice.gVoid request) {
      return futureUnaryCall(
          channel.newCall(METHOD_MSG_RECV, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<IRC_service.Ircservice.gVoid> broadcastSend(
        IRC_service.Ircservice.gBroadcastSendParam request) {
      return futureUnaryCall(
          channel.newCall(METHOD_BROADCAST_SEND, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<IRC_service.Ircservice.gVoid> msgChannelSend(
        IRC_service.Ircservice.gChannelSendParam request) {
      return futureUnaryCall(
          channel.newCall(METHOD_MSG_CHANNEL_SEND, callOptions), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final UserService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder("IRC_service.UserService")
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_JOIN_CHANNEL,
          asyncClientStreamingCall(
            new io.grpc.stub.ServerCalls.ClientStreamingMethod<
                IRC_service.Ircservice.gString,
                IRC_service.Ircservice.gInt>() {
              @java.lang.Override
              public io.grpc.stub.StreamObserver<IRC_service.Ircservice.gString> invoke(
                  io.grpc.stub.StreamObserver<IRC_service.Ircservice.gInt> responseObserver) {
                return serviceImpl.joinChannel(responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_MSG_RECV,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                IRC_service.Ircservice.gVoid,
                IRC_service.Ircservice.gRepeatMsg>() {
              @java.lang.Override
              public void invoke(
                  IRC_service.Ircservice.gVoid request,
                  io.grpc.stub.StreamObserver<IRC_service.Ircservice.gRepeatMsg> responseObserver) {
                serviceImpl.msgRecv(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_BROADCAST_SEND,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                IRC_service.Ircservice.gBroadcastSendParam,
                IRC_service.Ircservice.gVoid>() {
              @java.lang.Override
              public void invoke(
                  IRC_service.Ircservice.gBroadcastSendParam request,
                  io.grpc.stub.StreamObserver<IRC_service.Ircservice.gVoid> responseObserver) {
                serviceImpl.broadcastSend(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_MSG_CHANNEL_SEND,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                IRC_service.Ircservice.gChannelSendParam,
                IRC_service.Ircservice.gVoid>() {
              @java.lang.Override
              public void invoke(
                  IRC_service.Ircservice.gChannelSendParam request,
                  io.grpc.stub.StreamObserver<IRC_service.Ircservice.gVoid> responseObserver) {
                serviceImpl.msgChannelSend(request, responseObserver);
              }
            }))).build();
  }
}
