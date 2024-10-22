package application.server.services.amator;

import librairies.server.Server;

import java.io.IOException;

public class AmatorServer extends Server {
    private static final String SERVICE_NAME = "Amator";
    private static final Class<AmatorService> SERVICE_CLASS = AmatorService.class;
    private static Integer SERVICE_PORT = 4000;

    public AmatorServer() throws IOException {
        super(SERVICE_CLASS, SERVICE_PORT, SERVICE_NAME);
    }
}
