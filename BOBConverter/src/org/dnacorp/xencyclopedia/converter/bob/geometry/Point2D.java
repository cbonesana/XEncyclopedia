package org.dnacorp.xencyclopedia.converter.bob.geometry;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 19:07.
 */
public class Point2D {

    private double x;
    private double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public boolean isZero() {
        return x == 0 && y == 0 ;
    }

    public boolean equals(Point2D other) {
        return x == other.x && y == other.y;
    }

    public boolean notEquals(Point2D other) {
        return !equals(other);
    }

    public Point2D minus(double b) {
        return new Point2D(x-b, y-b);
    }

    public Point2D minus(Point2D b) {
        return new Point2D(x-b.x, y-b.y);
    }

    public Point2D plus(double b) {
        return new Point2D(x+b, y+b);
    }

    public Point2D plus(Point2D b) {
        return new Point2D(x+b.x, y+b.y);
    }

    public Point2D multiply(double b) {
        return new Point2D(x*b, y*b);
    }

    public Point2D multiply(Point2D b) {
        return new Point2D(x+b.x, y+b.y);
    }

    public Point2D divide(double b) {
        return new Point2D(x/b, y/b);
    }

    public Point2D divide(Point2D b) {
        return new Point2D(x/b.x, y/b.y);
    }

    public Point2D multiplyEquals(double b) {
        x *= b;
        y *= b;
        return this;
    }

    public Point2D multiplyEquals(Point2D b) {
        x *= b.x;
        y *= b.y;
        return this;
    }

    public Point2D divideEquals(double b) {
        x /= b;
        y /= b;
        return this;
    }

    public Point2D divideEquals(Point2D b) {
        x /= b.x;
        y /= b.y;
        return this;
    }

    public Point2D minusEquals(double b) {
        x -= b;
        y -= b;
        return this;
    }

    public Point2D minusEquals(Point2D b) {
        x -= b.x;
        y -= b.y;
        return this;
    }

    public Point2D plusEquals(double b) {
        x += b;
        y += b;
        return this;
    }

    public Point2D plusEquals(Point2D b) {
        x += b.x;
        y += b.y;
        return this;
    }

    public boolean lessThan(double b) {
        return length() < b;
    }

    public boolean greaterThan(double b) {
        return length() > b;
    }

    public boolean lessThan(Point2D b) {
        return length() < b.length();
    }

    public boolean greaterThan(Point2D b) {
        return length() > b.length();
    }

    public Point2D normalize() {
        return this.divide(length());
    }

}
