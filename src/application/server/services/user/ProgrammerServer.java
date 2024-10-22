package application.server.services.user;

import librairies.server.Server;

import java.io.IOException;

public class ProgrammerServer extends Server {
    private static final String SERVICE_NAME = "User";
    private static final Class<ProgrammerService> SERVICE_CLASS = ProgrammerService.class;
    private static Integer SERVICE_PORT = 3000;

    public ProgrammerServer() throws IOException {
        super(SERVICE_CLASS, SERVICE_PORT, SERVICE_NAME);
    }
}
