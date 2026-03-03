package org.bhargav.component;

import org.bhargav.Chip8Root;
import org.bhargav.emulation.Chip8Memory;
import org.bhargav.emulation.Chip8Register;

import javax.swing.*;
import java.awt.*;

public class Chip8Debugger extends JDialog {

    public static final int COLS = 64;
    public static final int ROWS = 64;
    public static final int REGISTERS_COLS = 16;
    public static final int REGISTERS_ROWS = 3;

    Chip8Memory memory;
    Chip8Register register;

    JTable registersTable;
    JTable memoryTable;

    public Chip8Debugger(Chip8Memory memory, Chip8Register register, Chip8Root root) {
        super(root);
        this.memory = memory;
        this.register = register;

        Font mono = new Font("Monospaced", Font.PLAIN, 14);

        registersTable = new JTable(REGISTERS_ROWS, REGISTERS_COLS);
        registersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        registersTable.setFont(mono);
        JScrollPane registersScrollPane = new JScrollPane(registersTable);
        this.add(registersScrollPane);

        memoryTable = new JTable(ROWS, COLS);
        memoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        memoryTable.setFont(mono);

//        JScrollPane memoryScrollPane = new JScrollPane(memoryTable);
//        this.add(memoryScrollPane);

        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(root);

        updateDebuggerModel();
    }

    public void updateDebuggerModel() {
        for (int i = 0; i < REGISTERS_COLS; i++) {
            String registerByte = String.format("%02X", register.getVRegister(i) & 0xFF);
            registersTable.getModel().setValueAt(registerByte, 0, i);
        }
        for (int i = 0; i < REGISTERS_COLS; i++) {
            String registerShort = String.format("%02X", register.getStack(i) & 0xFFFF);
            registersTable.getModel().setValueAt(registerShort, 1, i);
        }
        registersTable.getModel().setValueAt(String.format("%02X", register.getPc() & 0xFFFF), 2, 0);
        registersTable.getModel().setValueAt(String.format("%02X", register.getSp() & 0xFFFF), 2, 1);
        registersTable.getModel().setValueAt(String.format("%02X", register.getDelayRegister() & 0xFFFF), 2, 2);
        registersTable.getModel().setValueAt(String.format("%02X", register.getSoundRegister() & 0xFFFF), 2, 3);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String memoryByte = String.format("%02X", memory.getMemory(ROWS * i + j) & 0xFF);
                memoryTable.getModel().setValueAt(memoryByte, i, j);
            }
        }
    }
}
