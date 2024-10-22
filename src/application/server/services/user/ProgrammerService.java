package application.server.services.user;

import librairies.server.Service;

import java.io.IOException;
import java.net.Socket;

public class ProgrammerService extends Service {
    public ProgrammerService(Socket socket) throws IOException {
        super(socket, ProgrammerComponent.class);
    }
}
