package org.bhargav;

import org.bhargav.emulation.Chip8Memory;
import org.bhargav.ui.Chip8MemoryDebugger;
import org.bhargav.ui.Chip8MenuBar;
import org.bhargav.ui.Chip8StatusBar;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Chip8Root extends JFrame {

    private Chip8Memory memory;

    public Chip8Root() throws HeadlessException {
        super("Chip-8 Emu");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());

        Chip8MenuBar menuBar = new Chip8MenuBar(this);

        this.setJMenuBar(menuBar);

        memory = new Chip8Memory();
    }

    public void loadRom(File romFile) {
        memory.clear();

        try {
            memory.load(romFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openDebuggerWindow() {
        new Chip8MemoryDebugger(memory, this).setVisible(true);
    }
}
