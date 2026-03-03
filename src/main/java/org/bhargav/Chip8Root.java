package org.bhargav;

import org.bhargav.component.Chip8Display;
import org.bhargav.component.Chip8StatusBar;
import org.bhargav.emulation.Chip8Memory;
import org.bhargav.component.Chip8Debugger;
import org.bhargav.component.Chip8MenuBar;
import org.bhargav.emulation.Chip8Register;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Chip8Root extends JFrame {

    private Chip8Memory memory;
    private Chip8Register register;
    private Chip8Display display;
    private Chip8StatusBar statusBar;

    public Chip8Root() throws HeadlessException {
        super("Chip-8 Emu");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());

        Chip8MenuBar menuBar = new Chip8MenuBar(this);
        display = new Chip8Display();
        statusBar = new Chip8StatusBar(this);

        this.setJMenuBar(menuBar);
        this.add(display, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.SOUTH);

        memory = new Chip8Memory();
        register = new Chip8Register();
    }

    public void loadRom(File romFile) {
        memory.clear();

        try {
            int bytesRead = memory.load(romFile);
            statusBar.updateMessage(bytesRead + " bytes read from " + romFile.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openDebuggerWindow() {
        new Chip8Debugger(memory, register, this).setVisible(true);
    }
}
