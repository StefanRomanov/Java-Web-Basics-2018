package org.softuni.broccolina.solet;

import java.util.Map;

public interface SoletConfig {
    Map<String,Object> getAllAttributes();

    void setAttribute(String name, Object attribute);

    Object getAttribute(String name);

    void deleteAttribute(String name);
}
