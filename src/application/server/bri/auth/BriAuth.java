package application.server.bri.auth;

import application.server.bri.BriAmator;
import application.server.bri.BriProgrammer;
import application.server.bri.FtpPathBuilder;

import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Logger;

public class BriAuth {
    private static Logger LOGGER = Logger.getLogger("Auth");
    private static final List<BriProgrammer> programmers;

    static {
        try {
            programmers = List.of(
                    new BriProgrammer("admin", "admin", new FtpPathBuilder().host("localhost").port("2121").path("services/")));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public static BriProgrammer programmerAuth(String login, String password) {
        return programmers.stream().filter(p -> p.getLogin().equals(login) && p.getPassword().equals(password)).findFirst().orElse(null);
    }

}
