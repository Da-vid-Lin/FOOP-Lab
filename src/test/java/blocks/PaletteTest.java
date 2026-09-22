package blocks;

import blocks.BlockShapes.Cell;
import blocks.BlockShapes.PixelLoc;
import blocks.BlockShapes.Shape;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PaletteTest {
    @Test void injectedRandomSourceMakesPaletteRepeatable() {
        List<Shape> shapes = List.of(
                new Shape(List.of(new Cell(0, 0))),
                new Shape(List.of(new Cell(0, 0), new Cell(1, 0)))
        );
        Palette first = new Palette(shapes, new Random(7), 3);
        Palette second = new Palette(shapes, new Random(7), 3);
        assertEquals(first.getShapesToPlace(), second.getShapesToPlace());
    }

    @Test void layoutMakesASpriteSelectable() {
        Palette palette = new Palette();
        palette.doLayout(10, 20, 15);
        assertNotNull(palette.getSprite(new PixelLoc(12, 22), 15));
    }
}
