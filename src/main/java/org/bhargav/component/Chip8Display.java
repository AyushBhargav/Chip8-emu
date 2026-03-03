package org.bhargav.component;

import javax.swing.*;
import java.awt.*;

public class Chip8Display extends JPanel {

    public static final int DISPLAY_COLS = 64;
    public static final int DISPLAY_ROWS = 32;
    public static final int SCALE = 10;

    private boolean[][] displayBuffer;

    public Chip8Display() {
        displayBuffer = new boolean[DISPLAY_ROWS][DISPLAY_COLS];

        setPreferredSize(new Dimension(DISPLAY_COLS * SCALE, DISPLAY_ROWS * SCALE));
        setBackground(new Color(15, 40, 15));
        setDoubleBuffered(true);
    }

    public void clearBuffer() {
        for (int i = 0; i < DISPLAY_ROWS; i++) {
            for (int j = 0; j < DISPLAY_COLS; j++) {
                displayBuffer[i][j] = false;
            }
        }
    }
}
