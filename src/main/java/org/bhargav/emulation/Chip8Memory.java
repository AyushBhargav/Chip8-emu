package org.bhargav.emulation;

import java.io.*;

public class Chip8Memory {

    public static int MEMORY_SIZE = 4096; // bytes
    private static int PROGRAM_START = 0x200;
    private final byte[] memory;

    public Chip8Memory() {
        this.memory = new byte[MEMORY_SIZE];
    }

    public byte getMemory(int address) {
        return memory[address];
    }

    public void clear() {
        for (int i = 0; i < MEMORY_SIZE; i++) {
            memory[i] = 0;
        }
    }

    public void load(File romFile) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(romFile));

        byte[] fileContent = bufferedInputStream.readAllBytes();

        for (int i = 0; i < fileContent.length; i++) {
            memory[PROGRAM_START + i] = fileContent[i];
        }
    }
}
