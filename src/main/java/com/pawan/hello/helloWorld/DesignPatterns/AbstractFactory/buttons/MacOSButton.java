package com.pawan.hello.helloWorld.DesignPatterns.AbstractFactory.buttons;

import java.awt.*;

/**
 * All products families have the same varieties (MacOS/Windows).
 *
 * This is a MacOS variant of a button.
 */
public class MacOSButton implements Buttons
{

    @Override
    public void paint() {
        System.out.println("You have created MacOSButton.");
    }
}
