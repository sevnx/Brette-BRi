package application.server.bri;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class BriProgrammer {
    private String login;
    private String password;
    private URLClassLoader classLoader;

    public BriProgrammer(String login, String password, String ftpPath) throws MalformedURLException {
        this.login = login;
        this.password = password;
        this.classLoader = new URLClassLoader(new URL[]{new URL(ftpPath)});
    }

    public BriProgrammer(String login, String password, FtpPathBuilder builder) throws MalformedURLException {
        this(login, password, builder.build());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public URLClassLoader getClassLoader() {
        return classLoader;
    }

    public void setFtpPath(String ftpPath) throws MalformedURLException {
        this.classLoader = new URLClassLoader(new URL[]{new URL(ftpPath)});
    }
}
