package blocks;

import blocks.BlockShapes.Cell;
import blocks.BlockShapes.Piece;
import blocks.BlockShapes.PixelLoc;
import blocks.BlockShapes.Sprite;
import blocks.BlockShapes.SpriteState;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class Controller extends MouseAdapter {
    private final GameView view;
    private final ModelInterface model;
    private final Palette palette;
    private final JFrame frame;
    private Sprite selected;

    public Controller(GameView view, ModelInterface model, Palette palette, JFrame frame) {
        this.view = view;
        this.model = model;
        this.palette = palette;
        this.frame = frame;
        palette.doLayout(view.margin, view.margin + model.spec().height() * view.cellSize, view.paletteCellSize);
        updateTitle();
    }

    @Override public void mousePressed(MouseEvent event) {
        selected = palette.getSprite(new PixelLoc(event.getX(), event.getY()), view.paletteCellSize);
        if (selected != null) selected.state(SpriteState.IN_PLAY);
    }

    @Override public void mouseDragged(MouseEvent event) {
        if (selected == null) return;
        selected.moveTo(event.getX(), event.getY());
        Piece candidate = boardPiece(event);
        PlacementPreview preview = model.preview(candidate);
        view.ghostPiece = preview.legal() ? candidate : null;
        view.completedRegions = preview.completedRegions();
        view.repaint();
    }

    @Override public void mouseReleased(MouseEvent event) {
        if (selected == null) return;
        Piece candidate = boardPiece(event);
        MoveResult result = model.place(candidate);
        selected.state(result.accepted() ? SpriteState.PLACED : SpriteState.IN_PALETTE);
        selected = null;
        view.ghostPiece = null;
        view.completedRegions = java.util.List.of();
        palette.replenish();
        palette.doLayout(view.margin, view.margin + model.spec().height() * view.cellSize, view.paletteCellSize);
        updateTitle();
        view.repaint();
    }

    private Piece boardPiece(MouseEvent event) {
        int x = Math.floorDiv(event.getX() - view.margin, view.cellSize);
        int y = Math.floorDiv(event.getY() - view.margin, view.cellSize);
        return new Piece(selected.shape(), new Cell(x, y));
    }

    private void updateTitle() {
        boolean gameOver = model.isGameOver(palette.getShapesToPlace());
        frame.setTitle("Blocks Puzzle — Score: " + model.getScore() + (gameOver ? " — Game Over" : ""));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ModelInterface model = new Model2dArray();
            Palette palette = new Palette();
            GameView view = new GameView(model, palette);
            Controller controller = new Controller(view, model, palette, frame);
            view.addMouseListener(controller);
            view.addMouseMotionListener(controller);
            frame.add(view);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        });
    }
}
