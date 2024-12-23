package librairies.server;

import librairies.communication.Protocol;
import librairies.communication.StreamIOProtocol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public abstract class SocketProtocolLink implements Runnable {
    protected final Socket client;
    protected final Protocol protocol;

    public SocketProtocolLink(Socket socket) throws IOException {
        this.client = socket;
        this.protocol = new StreamIOProtocol(this.client);
    }

    public SocketProtocolLink(Socket socket, Class<? extends Protocol> protocol) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        this.client = socket;
        this.protocol = protocol.getConstructor(Socket.class).newInstance(socket);
    }

    public String read() throws IOException {
        return this.protocol.read();
    }

    public Socket getClient() {
        return this.client;
    }

    public void send(String data) {
        this.protocol.send(data);
    }

    public void sendAndClose(String data) throws IOException {
        this.protocol.sendAndClose(data);
    }

    public void run() {
    }

    public void start() {
        new Thread(this).start();
    }

    public void close() throws IOException {
        this.client.close();
        this.protocol.close();
    }
}
