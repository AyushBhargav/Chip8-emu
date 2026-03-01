package org.bhargav.component;

import org.bhargav.Chip8Root;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Chip8EmulationMenu extends JMenu {
    private JMenuItem debugger;

    private Chip8Root root;

    public Chip8EmulationMenu(String text, Chip8Root root) {
        super(text);

        this.root = root;

        debugger = new JMenuItem("Debugger");

        debugger.addActionListener(this::openDebugger);

        add(debugger);
    }

    private void openDebugger(ActionEvent e) {
        root.openDebuggerWindow();
    }
}
