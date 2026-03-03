package org.bhargav.emulation;

import org.apache.logging.log4j.core.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Chip8Register {

    private byte[] vRegisters;
    private short iRegister;

    private byte delayRegister;
    private byte soundRegister;
    private SourceDataLine line;

    private short pc;
    private byte sp;
    private short[] stack;

    public static final int V_COUNT = 16;
    public static final int STACK_SIZE = 16;
    public static final float SAMPLE_RATE = 44100f;

    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    public Chip8Register() {
        vRegisters = new byte[V_COUNT];
        iRegister = 0;
        delayRegister = 0;
        soundRegister = 0;
        pc = 0;
        sp = -1;
        stack = new short[STACK_SIZE];

        AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
        try {
            line = AudioSystem.getSourceDataLine(format);
            line.open(format);
        } catch (LineUnavailableException e) {
            System.out.println("No audio source data line available. No sound will be available");
        }

    }

    public byte getVRegister(int i) {
        return vRegisters[i];
    }

    public void setVRegister(int i, byte val) {
        this.vRegisters[i] = val;
    }

    public short getStack(int i) {
        return stack[i];
    }

    public byte[] getVRegisters() {
        return vRegisters;
    }

    public short getIRegister() {
        return iRegister;
    }

    public byte getDelayRegister() {
        return delayRegister;
    }

    public byte getSoundRegister() {
        return soundRegister;
    }

    public short getPc() {
        return pc;
    }

    public byte getSp() {
        return sp;
    }

    public short[] getStack() {
        return stack;
    }

    public void setDelayTimer(byte delay) {
        this.delayRegister = delay;

        executorService.submit(() -> {
            while (delayRegister > 0) {
                delayRegister--;
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public void setSoundTimer(byte soundTime) {
        this.soundRegister = soundTime;

        executorService.submit(() -> {
            try {
                line.start();
                while (soundRegister > 0) {
                    soundRegister--;
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            } finally {
                line.stop();
            }
        });
    }

    public void setPc(short pc) {
        this.pc = pc;
    }

    public void incrementPc(int inc) {
        this.pc += inc;
    }

    public void pushStack(short val) {
        this.stack[++this.sp] = val;
    }

    public short popStack() {
        return this.stack[this.sp--];
    }

    public short topStack() {
        return this.stack[this.sp];
    }
}
