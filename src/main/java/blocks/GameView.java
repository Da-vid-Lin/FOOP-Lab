package blocks;

import blocks.BlockShapes.Cell;
import blocks.BlockShapes.Piece;
import blocks.BlockShapes.Shape;
import blocks.BlockShapes.Sprite;

import javax.swing.JComponent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Set;

public final class GameView extends JComponent {
    final ModelInterface model;
    final Palette palette;
    final int margin = 5;
    final int cellSize = 40;
    final int paletteCellSize = 20;
    Piece ghostPiece;
    List<Shape> completedRegions = List.of();

    public GameView(ModelInterface model, Palette palette) {
        this.model = model;
        this.palette = palette;
    }

    private void paintShapePalette(Graphics g) {
        int top = margin + model.spec().height() * cellSize;
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(margin, top, model.spec().width() * cellSize, paletteHeight());
        for (Sprite sprite : palette.getSprites()) {
            g.setColor(Color.BLUE);
            for (Cell cell : sprite.shape()) {
                g.fill3DRect(sprite.px() + cell.x() * paletteCellSize,
                        sprite.py() + cell.y() * paletteCellSize,
                        paletteCellSize, paletteCellSize, true);
            }
        }
    }

    private void paintPreview(Graphics g) {
        if (ghostPiece == null) return;
        g.setColor(new Color(0, 180, 180));
        for (Cell cell : ghostPiece.cells()) {
            g.fillRect(margin + cell.x() * cellSize, margin + cell.y() * cellSize, cellSize, cellSize);
        }
        g.setColor(new Color(255, 180, 0, 100));
        for (Shape region : completedRegions) {
            for (Cell cell : region) {
                g.fillRect(margin + cell.x() * cellSize, margin + cell.y() * cellSize, cellSize, cellSize);
            }
        }
    }

    private void paintGrid(Graphics g) {
        Set<Cell> occupied = model.snapshot().occupiedCells();
        for (int x = 0; x < model.spec().width(); x++) {
            for (int y = 0; y < model.spec().height(); y++) {
                g.setColor(occupied.contains(new Cell(x, y)) ? Color.DARK_GRAY : Color.WHITE);
                g.fill3DRect(margin + x * cellSize, margin + y * cellSize, cellSize, cellSize, true);
            }
        }
    }

    private void paintMiniGrids(Graphics2D g) {
        int size = model.spec().subSize();
        g.setStroke(new BasicStroke(2));
        g.setColor(Color.BLACK);
        for (int x = 0; x < model.spec().width(); x += size) {
            for (int y = 0; y < model.spec().height(); y += size) {
                g.drawRect(margin + x * cellSize, margin + y * cellSize, size * cellSize, size * cellSize);
            }
        }
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGrid(g);
        paintPreview(g);
        paintMiniGrids((Graphics2D) g);
        paintShapePalette(g);
    }

    private int paletteHeight() { return Math.max(120, model.spec().height() * cellSize / 2); }

    @Override public Dimension getPreferredSize() {
        return new Dimension(model.spec().width() * cellSize + 2 * margin,
                model.spec().height() * cellSize + 2 * margin + paletteHeight());
    }
}
