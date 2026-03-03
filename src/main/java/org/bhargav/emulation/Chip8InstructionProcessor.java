package org.bhargav.emulation;

import org.bhargav.component.Chip8Display;

public class Chip8InstructionProcessor {

    private final Chip8Register register;
    private final Chip8Memory memory;
    private final Chip8Display display;

    public Chip8InstructionProcessor(Chip8Register register, Chip8Memory memory, Chip8Display display) {
        this.register = register;
        this.memory = memory;
        this.display = display;
    }

    public void process(int inst) {
        byte high = (byte) (inst >> 8);
        byte low = (byte) (inst & 0xFF);

        int cmd = high >> 4;
        int lowCmd = low & 0x0F;

        if (inst == 0x00E0) {
            // CLS
            clearScreen();
        } else if (inst == 0x00EE) {
            // RET
            returnSubroutine();
        } else if (cmd == 1) {
            // JMP
            jumpToLoc((short) (inst & 0x0FFF));
        } else if (cmd == 2) {
            // CALL
            callSubroutine((short) (inst & 0x0FFF));
        } else if (cmd == 3) {
            // SE
            int x = high & 0x0F;
            skipIfEqual(x, low);
        } else if (cmd == 4) {
            // SNE
            int x = high & 0x0F;
            skipIfNotEqual(x, low);
        } else if (cmd == 5) {
            // SE V
            int x = high & 0x0F;
            int y = low >> 4;
            skipIfEqualRegisters(x, y);
        } else if (cmd == 6) {
            // LD vx
            int x = high & 0x0F;
            loadInRegister(x, low);
        } else if (cmd == 7) {
            // ADD vx kk
            int x = high & 0x0F;
            addToRegister(x, low);
        } else if (cmd == 8 && lowCmd == 0) {
            // ADD vx vy
            int x = high & 0x0F;
            int y = low >> 4;
            loadInRegisterFromRegister(x, y);
        } else if (cmd == 8 && lowCmd == 1) {
            // OR vx vy
            int x = high & 0x0F;
            int y = low >> 4;
            orRegisters(x, y);
        } else if (cmd == 8 && lowCmd == 2) {
            // AND vx vy
            int x = high & 0x0F;
            int y = low >> 4;
            andRegisters(x, y);
        } else if (cmd == 8 && lowCmd == 3) {
            // XOR vx vy
            int x = high & 0x0F;
            int y = low >> 4;
            xorRegisters(x, y);
        } else if (cmd == 8 && lowCmd == 4) {
            // ADD vx vy
            int x = high & 0x0F;
            int y = low >> 4;
            addRegisters(x, y);
        }
    }

    private void addRegisters(int x, int y) {
        int sum = register.getVRegister(x) + register.getVRegister(y);
        register.setVRegister(0xF, (byte) (sum > 255 ? 1 : 0));
        register.setVRegister(x, (byte)(sum & 0xFF));
    }

    private void xorRegisters(int x, int y) {
        byte xor = (byte) (x ^ y);
        register.setVRegister(x, xor);
    }

    private void andRegisters(int x, int y) {
        byte and = (byte) (x & y);
        register.setVRegister(x, and);
    }

    private void orRegisters(int x, int y) {
        byte or = (byte) (x | y);
        register.setVRegister(x, or);
    }

    private void loadInRegisterFromRegister(int x, int y) {
        register.setVRegister(x, register.getVRegister(y));
    }

    private void addToRegister(int x, byte low) {
        register.setVRegister(x, (byte)(register.getVRegister(x) + low & 0xFF));
    }

    private void loadInRegister(int x, byte low) {
        register.setVRegister(x, low);
    }

    private void skipIfEqualRegisters(int x, int y) {
        if (register.getVRegister(x) == register.getVRegister(y)) {
            register.incrementPc(2);
        }
    }

    private void skipIfNotEqual(int x, byte low) {
        if (register.getVRegister(x) != low) {
            register.incrementPc(2);
        }
    }

    private void skipIfEqual(int x, byte low) {
        if (register.getVRegister(x) == low) {
            register.incrementPc(2);
        }
    }

    private void callSubroutine(short loc) {
        register.pushStack(register.getPc());
        register.setPc(loc);
    }

    private void clearScreen() {
        display.clearBuffer();
    }

    private void returnSubroutine() {
        register.setPc(register.topStack());
        register.popStack();
    }

    private void jumpToLoc(short loc) {
        register.setPc(loc);
    }
}
