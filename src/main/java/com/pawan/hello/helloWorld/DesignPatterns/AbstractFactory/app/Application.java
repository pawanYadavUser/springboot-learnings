package com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.app;

import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.buttons.Buttons;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.checkboxes.Checkbox;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.factories.GUIFactory;

public class Application {
    private Buttons button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
