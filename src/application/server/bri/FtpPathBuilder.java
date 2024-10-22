package application.server.bri;

import java.util.Optional;

public class FtpPathBuilder {
    private Optional<String> host = Optional.empty();
    private Optional<String> port = Optional.empty();
    private Optional<String> path = Optional.empty();

    public FtpPathBuilder() {
    }

    public FtpPathBuilder host(String host) {
        this.host = Optional.of(host);
        return this;
    }

    public FtpPathBuilder port(String port) {
        this.port = Optional.of(port);
        return this;
    }

    public FtpPathBuilder path(String path) {
        this.path = Optional.of(path);
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append("ftp://");
        if (host.isPresent()) {
            builder.append(host.get());
        } else {
            builder.append("localhost");
        }

        if (port.isPresent()) {
            builder.append(":").append(port.get());
        } else {
            builder.append(":21");
        }

        if (path.isPresent()) {
            builder.append("/").append(path.get());
        } else {
            builder.append("/");
        }

        System.out.println(builder);

        return builder.toString();
    }
}
