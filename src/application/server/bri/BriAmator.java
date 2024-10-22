package application.server.bri;

public class BriAmator {
    private String login;
    private String password;

    public BriAmator(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
