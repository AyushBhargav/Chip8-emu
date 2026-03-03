package org.bhargav.emulation;

public class Chip8Keyboard {
    private boolean[] keys;

    public static final int KEYS_COUNT = 16;

    public Chip8Keyboard() {
        keys = new boolean[KEYS_COUNT];
    }
}
