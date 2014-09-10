package org.dnacorp.xencyclopedia.converter.bob.geometry;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 19:07.
 */
public class Point2DDouble {

    protected double x;
    protected double y;

    public Point2DDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2DDouble(Point2DDouble pt) {
        this.x = pt.x;
        this.y = pt.y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public boolean isZero() {
        return x == 0 && y == 0 ;
    }

    public boolean equals(Point2DDouble other) {
        return x == other.x && y == other.y;
    }

    public boolean notEquals(Point2DDouble other) {
        return !equals(other);
    }

    public Point2DDouble minus(double b) {
        return new Point2DDouble(x-b, y-b);
    }

    public Point2DDouble minus(Point2DDouble b) {
        return new Point2DDouble(x-b.x, y-b.y);
    }

    public Point2DDouble plus(double b) {
        return new Point2DDouble(x+b, y+b);
    }

    public Point2DDouble plus(Point2DDouble b) {
        return new Point2DDouble(x+b.x, y+b.y);
    }

    public Point2DDouble multiply(double b) {
        return new Point2DDouble(x*b, y*b);
    }

    public Point2DDouble multiply(Point2DDouble b) {
        return new Point2DDouble(x+b.x, y+b.y);
    }

    public Point2DDouble divide(double b) {
        return new Point2DDouble(x/b, y/b);
    }

    public Point2DDouble divide(Point2DDouble b) {
        return new Point2DDouble(x/b.x, y/b.y);
    }

    public Point2DDouble multiplyEquals(double b) {
        x *= b;
        y *= b;
        return this;
    }

    public Point2DDouble multiplyEquals(Point2DDouble b) {
        x *= b.x;
        y *= b.y;
        return this;
    }

    public Point2DDouble divideEquals(double b) {
        x /= b;
        y /= b;
        return this;
    }

    public Point2DDouble divideEquals(Point2DDouble b) {
        x /= b.x;
        y /= b.y;
        return this;
    }

    public Point2DDouble minusEquals(double b) {
        x -= b;
        y -= b;
        return this;
    }

    public Point2DDouble minusEquals(Point2DDouble b) {
        x -= b.x;
        y -= b.y;
        return this;
    }

    public Point2DDouble plusEquals(double b) {
        x += b;
        y += b;
        return this;
    }

    public Point2DDouble plusEquals(Point2DDouble b) {
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

    public boolean lessThan(Point2DDouble b) {
        return length() < b.length();
    }

    public boolean greaterThan(Point2DDouble b) {
        return length() > b.length();
    }

    public Point2DDouble normalize() {
        return this.divide(length());
    }

    public void load(DataInputStream dis) throws IOException {
        x = dis.readDouble();
        y = dis.readDouble();
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeDouble(x);
        dos.writeDouble(y);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        dos.writeDouble(x);
        dos.writeChar(' ');
        dos.writeDouble(y);
        dos.writeChar(' ');
    }

}
