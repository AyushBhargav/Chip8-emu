package org.bhargav;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        FlatLaf.setup(new FlatLightLaf());

        Chip8Root chip8Root = new Chip8Root();
        chip8Root.setVisible(true);
    }
}