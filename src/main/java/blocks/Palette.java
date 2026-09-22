package blocks;

import blocks.BlockShapes.PixelLoc;
import blocks.BlockShapes.Shape;
import blocks.BlockShapes.ShapeSet;
import blocks.BlockShapes.Sprite;
import blocks.BlockShapes.SpriteState;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public final class Palette {
    private final List<Shape> availableShapes;
    private final List<Sprite> sprites = new ArrayList<>();
    private final RandomGenerator random;
    private final int paletteSize;

    public Palette() { this(new ShapeSet().getShapes(), RandomGenerator.getDefault(), 3); }

    public Palette(List<Shape> availableShapes, RandomGenerator random, int paletteSize) {
        if (availableShapes.isEmpty() || paletteSize <= 0) throw new IllegalArgumentException();
        this.availableShapes = List.copyOf(availableShapes);
        this.random = random;
        this.paletteSize = paletteSize;
        replenish();
    }

    public List<Shape> getShapes() { return availableShapes; }
    public List<Sprite> getSprites() { return List.copyOf(sprites); }

    public List<Shape> getShapesToPlace() {
        return sprites.stream()
                .filter(sprite -> sprite.state() != SpriteState.PLACED)
                .map(Sprite::shape)
                .toList();
    }

    public Sprite getSprite(PixelLoc point, int cellSize) {
        for (int i = sprites.size() - 1; i >= 0; i--) {
            Sprite sprite = sprites.get(i);
            if (sprite.state() == SpriteState.IN_PALETTE && sprite.contains(point, cellSize)) return sprite;
        }
        return null;
    }

    public void doLayout(int x0, int y0, int cellSize) {
        int x = x0;
        for (Sprite sprite : sprites) {
            sprite.moveTo(x, y0);
            int shapeWidth = sprite.shape().stream().mapToInt(cell -> cell.x()).max().orElse(0) + 1;
            x += (shapeWidth + 1) * cellSize;
        }
    }

    public void replenish() {
        if (sprites.stream().anyMatch(sprite -> sprite.state() != SpriteState.PLACED)) return;
        sprites.clear();
        for (int i = 0; i < paletteSize; i++) {
            Shape shape = availableShapes.get(random.nextInt(availableShapes.size()));
            sprites.add(new Sprite(shape, 0, 0));
        }
    }
}
