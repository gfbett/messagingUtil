package com.santander.messaging.producers;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import java.util.Map;

public class ZeroMQProducer extends Producer {

    private ZContext zContext;
    private ZMQ.Socket socket;


    public ZeroMQProducer() {
        zContext = new ZContext();
    }

    @Override
    protected void initProducer(Map<String, String> producerConfig) {
        String host = producerConfig.get("host");
        int portNumber = Integer.parseInt(producerConfig.get("port"));
        socket = zContext.createSocket(SocketType.REQ);
        socket.connect("tcp://" + host + ":" + portNumber);
    }

    @Override
    protected void sendMessage(String message) {
        try {
            socket.send(message.getBytes());
            byte[] reply = socket.recv(0);
        } catch (ZMQException exception) {
            System.err.println("Error sending message: " + exception.getLocalizedMessage());
            throw exception;
        }
    }
}
