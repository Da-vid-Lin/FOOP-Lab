package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

class PolyLine {
    ArrayList<Point2D> points = new ArrayList<>();
}

record LineTo(Point2D from, Point2D to) {
    public void draw(Graphics2D g2) {
        g2.draw(new Line2D.Double(from, to));
    }
}

class LineModel {
    int pointSpan = 10;

    final ArrayList<PolyLine> lines = new ArrayList<>();
    // todo: add necessary fields

    public void startNewLine(Point2D point) {
        // todo
    }

    public void addPoint(Point2D point) {
        // todo
    }

    public void closeLine(Point2D point) {
        // todo
    }

    public List<LineTo> getLines() {
        List<LineTo> result = new ArrayList<>();
        // todo
        return result;
    }

    public void reset() {
        lines.clear();
    }
}

class DrawModelComponent extends JComponent {
    final LineModel model = new LineModel();

    public DrawModelComponent() {
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // todo - fix this
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                model.startNewLine(e.getPoint());
            }

            public void mouseReleased(MouseEvent e) {
                // todo: fix it
                repaint();
            }
        });
        // set background color to white
        setBackground(Color.WHITE);
    }

    public void reset() {
        // todo: call the obvious model method
        repaint();
    }

    public void paintComponent(Graphics g) {
        // cast to Graphics2D - more powerful API
        g.clearRect(0, 0, getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // todo: draw the lines from the model
    }

    // define a convenient default size for the component
    public Dimension getPreferredSize() {
        return new Dimension(500, 300);
    }

}

class ScribbleModelPanel extends JPanel {
    private final DrawModelComponent drawComponent = new DrawModelComponent();

    // set a border layout, with the drawing component in the center
    // and a button panel in the south
    // the button panel contains a single button, "Reset"
    // add an action listener to the button that calls the reset() method of drawComponent
    public ScribbleModelPanel() {
        setLayout(new BorderLayout());

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> drawComponent.reset());

        //...

        JPanel buttonPanel = new JPanel();  // Uses FlowLayout by default
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);
        add(drawComponent, BorderLayout.CENTER);
    }
}

public class ScribbleModelApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Scribble App - Mouse Event Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ScribbleModelPanel());
        frame.pack();
        frame.setVisible(true);

        LineModel lineModel = new LineModel();
        lineModel.addPoint(new Point2D.Double(10, 20));
        System.out.println(lineModel.getLines().size());
    }
}
