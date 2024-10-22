package application.server.services.amator;

import application.server.bri.BriAmator;
import application.server.bri.auth.BriAuth;
import application.server.bri.BriService;
import application.server.bri.ServiceRegistry;
import librairies.server.Component;
import librairies.server.Service;

import java.net.Socket;
import java.util.logging.Logger;

public class AmatorComponent implements Component {
    private static final Logger LOGGER = Logger.getLogger("AmatorComponent");

    @Override
    public void call(Service service) {
        try {
            StringBuilder response = new StringBuilder();
            response.append("Welcome to BRi Launch");
            response.append(System.lineSeparator());
            response.append("Please enter your login and password to access the services.");
            response.append(System.lineSeparator());
            response.append("Login: ");
            service.send(response.toString());

            String login = service.read();

            service.send("Password: ");
            String password = service.read();

            BriAmator amator = BriAuth.amatorAuth(login, password);
            if (amator == null) {
                service.send("Incorrect login or password.");
                service.close();
            }

            response = new StringBuilder();

            response.append("Welcome " + login + "!");
            response.append(System.lineSeparator());
            response.append("Please select the service you want to access:");
            response.append(System.lineSeparator());
            response.append(ServiceRegistry.toStringue());
            response.append(System.lineSeparator());
            response.append("Service number: ");
            service.send(response.toString());

            int numService = Integer.parseInt(service.read());
            Class<? extends BriService> serviceClass = ServiceRegistry.getServiceClass(numService);
            BriService briService = serviceClass.getConstructor(Socket.class).newInstance(service.getClient());
            briService.run();



        } catch (Exception e) {
            LOGGER.severe("Unexpected error : " + e);
            service.send("An error occurred, please try again later.");
        }
    }
}
