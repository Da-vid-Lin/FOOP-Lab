package graphics;

import java.awt.*;
import javax.swing.*;

class ColorComponent extends JComponent {
    private final Color color;

    public ColorComponent(Color color, Dimension preferredSize) {
        this.color = color;
        if (preferredSize != null) setPreferredSize(preferredSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

public class BorderLayoutExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BorderLayout Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new ColorComponent(Color.RED, new Dimension(400, 50)), BorderLayout.NORTH);
            frame.add(new ColorComponent(Color.BLUE, new Dimension(400, 50)), BorderLayout.SOUTH);
            frame.add(new ColorComponent(Color.GREEN, new Dimension(50, 100)), BorderLayout.EAST);
            frame.add(new ColorComponent(Color.YELLOW, new Dimension(50, 200)), BorderLayout.WEST);
            frame.add(new ColorComponent(Color.ORANGE, null), BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }
}













