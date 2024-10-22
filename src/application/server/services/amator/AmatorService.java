package application.server.services.amator;

import librairies.server.Service;

import java.io.IOException;
import java.net.Socket;

public class AmatorService extends Service {
    public AmatorService(Socket socket) throws IOException {
        super(socket, AmatorComponent.class);
    }
}
