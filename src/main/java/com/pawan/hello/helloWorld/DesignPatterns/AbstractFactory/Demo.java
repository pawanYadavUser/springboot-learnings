package com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory;

import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.app.Application;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.factories.GUIFactory;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.factories.MacOSFactory;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.factories.WindowsFactory;

public class Demo {
    /**
     * Application picks the factory type and creates it in run time (usually at
     * initialization stage), depending on the configuration or environment
     * variables.
     */
    private static Application configureApplication() {
        Application app;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }
        app = new Application(factory);
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}
