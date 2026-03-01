package org.bhargav.component;

import org.bhargav.Chip8Root;

import javax.swing.*;

public class Chip8MenuBar extends JMenuBar {

    JMenu fileMenu;
    JMenu emulationMenu;
    JMenu optionsMenu;
    JMenu aboutMenu;

    public Chip8MenuBar(Chip8Root root) {
        fileMenu = new Chip8FileMenu("File", root);
        emulationMenu = new Chip8EmulationMenu("Emulation", root);
//        optionsMenu = new Chip8FileMenu("Options");
//        aboutMenu = new Chip8FileMenu("About");

        this.add(fileMenu);
        this.add(emulationMenu);
//        this.add(optionsMenu);
//        this.add(aboutMenu);
    }

}
