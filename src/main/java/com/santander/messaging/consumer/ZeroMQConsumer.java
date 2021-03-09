package com.santander.messaging.consumer;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import java.util.Map;

public class ZeroMQConsumer extends Consumer {

    private ZContext zContext;
    private ZMQ.Socket socket;

    public ZeroMQConsumer() {
        zContext = new ZContext();
    }

    @Override
    protected void initConsumer(Map<String, String> producerConfig) {
        int portNumber = Integer.parseInt(producerConfig.get("port"));
        socket = zContext.createSocket(SocketType.REP);
        socket.bind("tcp://*:" + portNumber);
    }

    @Override
    protected void startConsumer(java.util.function.Consumer<String> consumer) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Block until a message is received
                byte[] reply = socket.recv(0);

                consumer.accept(new String(reply));
                socket.send("".getBytes(ZMQ.CHARSET), 0);
            } catch (ZMQException exception) {
                System.err.println("Error reading message" + exception.getLocalizedMessage());
                throw exception;
            }
        }
    }
}
