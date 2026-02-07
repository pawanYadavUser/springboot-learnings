package com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.factories;

import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.buttons.Buttons;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.buttons.WindowsButton;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.checkboxes.Checkbox;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.checkboxes.MacOSCheckbox;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.checkboxes.WindowsCheckbox;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
public class WindowsFactory implements GUIFactory {

    @Override
    public Buttons createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}