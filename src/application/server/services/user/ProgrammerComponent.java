package application.server.services.user;

import application.server.bri.BriProgrammer;
import application.server.bri.ServiceRegistry;
import application.server.bri.auth.BriAuth;
import librairies.server.Component;
import librairies.server.Service;

import java.util.logging.Logger;

public class ProgrammerComponent implements Component {
    private static final Logger LOGGER = Logger.getLogger("ProgrammerComponent");

    @Override
    public void call(Service service) {
        try {
            StringBuilder response = new StringBuilder();
            response.append("Welcome to the BRi Launch");
            response.append(System.lineSeparator());
            response.append("Please enter your login and password to access the services.");
            response.append(System.lineSeparator());
            response.append("Login: ");
            service.send(response.toString());
            String login = service.read();
            service.send("Password: ");
            String password = service.read();

            BriProgrammer programmer = BriAuth.programmerAuth(login, password);
            assert programmer != null;

            response = new StringBuilder();

            response.append("Welcome " + login + "!");
            response.append(System.lineSeparator());
            response.append("Choose an option:").append(System.lineSeparator());
            response.append("1 - Add a service").append(System.lineSeparator());
            response.append("2 - Update a service").append(System.lineSeparator());
            response.append("3 - Change FTP host").append(System.lineSeparator());
            service.send(response.toString());

            int choice = Integer.parseInt(service.read());
            switch (choice) {
                case 1 -> {
                    response = new StringBuilder();
                    response.append("Enter the service name: ");
                    service.send(response.toString());
                    String serviceName = service.read();
                    ServiceRegistry.addService(programmer.getClassLoader().loadClass(serviceName));
                }
                case 2 -> {}
                case 3 -> {}
                default -> service.send("Invalid choice.");
            }


        } catch (Exception e) {
            LOGGER.severe("Unexpected error : " + e);
            service.send("An error occurred, please try again later.");
        }
    }
}
