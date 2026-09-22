package design_patterns.visitor;

import java.util.*;

// --- Element hierarchy (Shapes) ---

interface Shape {
    void accept(ShapeVisitor visitor); // double dispatch entry point
}

class Circle implements Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitCircle(this);
    }
}

class Rectangle implements Shape {
    double width;
    double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitRectangle(this);
    }
}

// --- Visitor interface ---

interface ShapeVisitor {
    void visitCircle(Circle c);
    void visitRectangle(Rectangle r);
}

// --- Concrete Visitor 1: Area Calculator ---

class AreaCalculator implements ShapeVisitor {
    private double totalArea = 0.0;

    @Override
    public void visitCircle(Circle c) {
        double area = Math.PI * c.radius * c.radius;
        System.out.printf("Circle with radius %.2f → area %.2f%n", c.radius, area);
        totalArea += area;
    }

    @Override
    public void visitRectangle(Rectangle r) {
        double area = r.width * r.height;
        System.out.printf("Rectangle %.2fx%.2f → area %.2f%n", r.width, r.height, area);
        totalArea += area;
    }

    public double getTotalArea() {
        return totalArea;
    }
}

// --- Concrete Visitor 2: Perimeter Calculator ---

class PerimeterCalculator implements ShapeVisitor {
    private double totalPerimeter = 0.0;

    @Override
    public void visitCircle(Circle c) {
        double perimeter = 2 * Math.PI * c.radius;
        System.out.printf("Circle with radius %.2f → perimeter %.2f%n", c.radius, perimeter);
        totalPerimeter += perimeter;
    }

    @Override
    public void visitRectangle(Rectangle r) {
        double perimeter = 2 * (r.width + r.height);
        System.out.printf("Rectangle %.2fx%.2f → perimeter %.2f%n", r.width, r.height, perimeter);
        totalPerimeter += perimeter;
    }

    public double getTotalPerimeter() {
        return totalPerimeter;
    }
}

// --- Client / main program ---

public class ShapeVisitorDemo {

    public static void main(String[] args) {
        List<Shape> shapes = List.of(
                new Circle(2.0),
                new Rectangle(3.0, 4.0),
                new Circle(1.5),
                new Rectangle(2.0, 5.0)
        );

        // Apply area visitor
        System.out.println("=== Area Calculation ===");
        AreaCalculator areaVisitor = new AreaCalculator();
        for (Shape s : shapes) s.accept(areaVisitor);
        System.out.printf("Total area = %.2f%n%n", areaVisitor.getTotalArea());

        // Apply perimeter visitor
        System.out.println("=== Perimeter Calculation ===");
        PerimeterCalculator perimeterVisitor = new PerimeterCalculator();
        for (Shape s : shapes) s.accept(perimeterVisitor);
        System.out.printf("Total perimeter = %.2f%n", perimeterVisitor.getTotalPerimeter());
    }
}
