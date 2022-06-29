

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {


    public MainWindow() {
        this.setSize(Constants.WINDOW_W, Constants.WINDOW_H);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        MainPanel mainPanel = new MainPanel(0, 0, Constants.WINDOW_W, Constants.WINDOW_H, Color.GRAY);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
    }
}