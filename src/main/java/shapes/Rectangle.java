package shapes;

import geometry.Vec2d;

public class Rectangle extends MovableShape {
    double width;
    double height;

    public Rectangle(Vec2d p, double width, double height) {
        super(p);
        if (width < 0 || height < 0) throw new IllegalArgumentException("Dimensions cannot be negative");
        this.width = width;
        this.height = height;
    }

    public double area() {
        // todo: implement
        return 0.0;
    }

    public double perimeter() {
        // todo: implement
        return 0;
    }
}
