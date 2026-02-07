package com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.factories;

import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.buttons.Buttons;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.buttons.MacOSButton;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.checkboxes.Checkbox;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.checkboxes.MacOSCheckbox;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
public class MacOSFactory implements GUIFactory {

    @Override
    public Buttons createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}