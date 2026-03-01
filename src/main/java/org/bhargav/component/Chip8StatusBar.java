package org.bhargav.component;

import org.bhargav.Chip8Root;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Chip8StatusBar extends JPanel {

    private Chip8Root root;
    private JLabel messageLabel;

    public Chip8StatusBar(Chip8Root root) {
        super();

        this.root = root;
        this.messageLabel = new JLabel("Ready.");
        this.setLayout(new BorderLayout());

        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setPreferredSize(new Dimension(root.getWidth(), 16));
        add(messageLabel, BorderLayout.WEST);
    }

    public void updateMessage(String message) {
        messageLabel.setText(message);
    }


}
