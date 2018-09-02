package org.softuni.javache;

public final class WebConstants {
    public static final Integer DEFAULT_SERVER_PORT = 8000;
    private static final String WEBSERVER_PACKAGE_PATH = "/org/softuni/javache";

    public static final String SERVER_HTTP_VERSION = "HTTP/1.1";
    public static final String SERVER_ROOT_FOLDER = Server.class.getResource("").toString()
            .replace("file:/","")
            .replace(WEBSERVER_PACKAGE_PATH,"");

    private WebConstants() { }
}
