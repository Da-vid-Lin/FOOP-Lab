package design_patterns.direct;

import java.util.*;

interface Shape {
    double area();
    double perimeter();
}

class Circle implements Shape {
    double radius;
    Circle(double radius) { this.radius = radius; }

    public double area() { return Math.PI * radius * radius; }
    public double perimeter() { return 2 * Math.PI * radius; }
}

class Rectangle implements Shape {
    double width, height;
    Rectangle(double width, double height) {
        this.width = width; this.height = height;
    }

    public double area() { return width * height; }
    public double perimeter() { return 2 * (width + height); }
}

public class DirectShapes {
    public static void main(String[] args) {
        List<Shape> shapes = List.of(
                new Circle(2.0),
                new Rectangle(3.0, 4.0),
                new Circle(1.5),
                new Rectangle(2.0, 5.0)
        );

        double totalArea = 0, totalPerimeter = 0;

        for (Shape s : shapes) {
            System.out.printf("%s → area %.2f, perimeter %.2f%n",
                    s.getClass().getSimpleName(),
                    s.area(), s.perimeter());
            totalArea += s.area();
            totalPerimeter += s.perimeter();
        }

        System.out.printf("%nTotal area = %.2f%n", totalArea);
        System.out.printf("Total perimeter = %.2f%n", totalPerimeter);
    }
}
