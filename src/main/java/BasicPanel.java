

        import javax.swing.*;
        import java.awt.*;

public class BasicPanel extends JPanel {
    private final ImageIcon backGround;
    public BasicPanel(int x, int y, int w, int h,String fieldName) {
        this.setBounds(x, y, w, h);
        this.backGround = new ImageIcon(fieldName);
        init();
    }


    public void init() {
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.backGround != null) {
            this.backGround.paintIcon(this, g, 0, 0);
        }
    }


    public Button addButton(String text, int x, int y, int w, int h, Color foregroundColor,
                            Color color) {
        Button button = new Button(text);
        button.setBounds(x, y, w, h);
        button.setBackground(color);
        button.setForeground(foregroundColor);
        this.add(button);
        repaint();
        return button;
    }


    public JLabel addJLabel(String title, int x, int y, int w, int h, int size, Color color) {
        JLabel jLabel = new JLabel(title, SwingConstants.CENTER);
        jLabel.setFont(new Font("ariel", Font.BOLD, size));
        jLabel.setForeground(color);
        jLabel.setBounds(x, y, w, h);
        this.add(jLabel);
        return jLabel;
    }

    public JTextField addTextField(int x, int y, int w, int h) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, w, h);
        this.add(textField);
        return textField;
    }

}
