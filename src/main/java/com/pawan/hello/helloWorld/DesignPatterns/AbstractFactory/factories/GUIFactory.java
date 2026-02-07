package com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.factories;

import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.buttons.Buttons;
import com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.checkboxes.Checkbox;

/**
 * Abstract factory knows about all (abstract) product types.
 */
public interface GUIFactory {
    Buttons createButton();
    Checkbox createCheckbox();

}