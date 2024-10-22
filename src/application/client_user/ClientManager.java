package application.client_user;

import librairies.communication.StreamIOProtocol;
import librairies.server.SocketProtocolLink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class ClientManager extends SocketProtocolLink {
    public ClientManager(String host, int port) throws IOException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        super(new Socket(host, port), StreamIOProtocol.class);
    }

    public BufferedReader getInputStream() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
