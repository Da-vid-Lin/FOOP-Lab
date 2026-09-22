package graphics;

import javax.swing.*;
import java.awt.*;

public class GreenComponent extends JComponent {
    public GreenComponent(int width, int height) {
        // A component supplies a layout hint rather than setting its own pixel bounds.
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(java.awt.Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Green Component");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GreenComponent(400, 200));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
