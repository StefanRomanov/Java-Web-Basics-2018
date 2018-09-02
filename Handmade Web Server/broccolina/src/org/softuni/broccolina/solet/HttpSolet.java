package org.softuni.broccolina.solet;

import java.io.IOException;

public interface HttpSolet {
    void init(SoletConfig soletConfig);

    void service(HttpSoletRequest request, HttpSoletResponse response) throws IOException;

    SoletConfig getSoletConfig();

    boolean isInitialized();

}
