package org.bhargav.ui;

import org.bhargav.Chip8Root;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Chip8StatusBar extends JPanel {

    private Chip8Root root;

    public Chip8StatusBar(Chip8Root root) {
        super();

        this.root = root;

        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(root.getWidth(), 16));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
}
