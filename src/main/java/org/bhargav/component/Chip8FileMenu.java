package org.bhargav.component;

import org.bhargav.Chip8Root;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

public class Chip8FileMenu extends JMenu {
    private JMenuItem openRom;
    private JMenuItem recent;
    private JMenuItem quit;

    private Chip8Root root;

    public Chip8FileMenu(String text, Chip8Root root) {
        super(text);

        this.root = root;

        openRom = new JMenuItem("Open ROM");
        recent = new JMenuItem("Recent");
        quit = new JMenuItem("Exit");

        openRom.addActionListener(this::openRomListener);
        quit.addActionListener(this::exitActionListener);

        add(openRom);
        add(recent);
        add(quit);
    }

    private void openRomListener(ActionEvent e) {
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Chip 8 rom files",
                "ch8");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(fileNameExtensionFilter);

        int result = fileChooser.showOpenDialog(root);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            root.loadRom(selectedFile);
        }
    }

    private void exitActionListener(ActionEvent e) {
        System.exit(0);
    }


}
