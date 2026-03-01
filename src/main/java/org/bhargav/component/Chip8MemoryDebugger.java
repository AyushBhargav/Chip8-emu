package org.bhargav.component;

import org.bhargav.Chip8Root;
import org.bhargav.emulation.Chip8Memory;

import javax.swing.*;
import java.awt.*;

public class Chip8MemoryDebugger extends JDialog {

    public static final int COLS = 64;
    public static final int ROWS = 64;

    Chip8Memory memory;

    JTable memoryTable;

    public Chip8MemoryDebugger(Chip8Memory memory, Chip8Root root) {
        super(root);
        this.memory = memory;

        Font mono = new Font("Monospaced", Font.PLAIN, 14);

        memoryTable = new JTable(ROWS, COLS);
        memoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        memoryTable.setFont(mono);
        JScrollPane scrollPane = new JScrollPane(memoryTable);
        this.add(scrollPane);

        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(root);

        updateMemory();
    }

    public void updateMemory() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String memoryByte = String.format("%02X", memory.getMemory(ROWS * i + j) & 0xFF);
                memoryTable.getModel().setValueAt(memoryByte, i, j);
            }
        }
    }
}
