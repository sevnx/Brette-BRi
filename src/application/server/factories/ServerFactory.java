package application.server.factories;

import application.server.services.amator.AmatorServer;
import application.server.services.user.ProgrammerServer;
import librairies.server.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

public class ServerFactory {
    private static final Logger LOGGER = Logger.getLogger("ServerFactory");
    private static final Vector<Server> launchedServers = new Vector<>();

    private static List<Class<? extends Server>> getServerList() {
        List<Class<? extends Server>> serverList = new ArrayList<>();
        serverList.add(AmatorServer.class);
        serverList.add(ProgrammerServer.class);
        return serverList;
    }

    public static void launch() {
        LOGGER.info("Launching servers...");

        for (Class<? extends Server> serverClass : getServerList()) {
            try {
                Server server = serverClass.getDeclaredConstructor().newInstance();
                LOGGER.info("Starting server " + server.getName() + " on port " + server.getIp());
                server.start();
                launchedServers.add(server);
            } catch (Exception e) {
                LOGGER.severe("Failed to launch server: " + serverClass.getSimpleName());
                e.printStackTrace();
            }
        }
        LOGGER.info("Servers launched.");
    }
}
