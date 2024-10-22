package application.client_user;

public class ClientStart {
    public static void main(String[] args) {
        try {
            ClientManager clientManager = ClientFactory.createClient(args);
            ClientFactory.manage(clientManager);
            ClientFactory.close(clientManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
