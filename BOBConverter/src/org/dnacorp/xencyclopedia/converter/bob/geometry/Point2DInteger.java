package org.dnacorp.xencyclopedia.converter.bob.geometry;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 19:07.
 */
public class Point2DInteger {

    private int x;
    private int y;

    public Point2DInteger(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public boolean isZero() {
        return x == 0 && y == 0 ;
    }

    public boolean equals(Point2DInteger other) {
        return x == other.x && y == other.y;
    }

    public boolean notEquals(Point2DInteger other) {
        return !equals(other);
    }

    public Point2DInteger minus(int b) {
        return new Point2DInteger(x-b, y-b);
    }

    public Point2DInteger minus(Point2DInteger b) {
        return new Point2DInteger(x-b.x, y-b.y);
    }

    public Point2DInteger plus(int b) {
        return new Point2DInteger(x+b, y+b);
    }

    public Point2DInteger plus(Point2DInteger b) {
        return new Point2DInteger(x+b.x, y+b.y);
    }

    public Point2DInteger multiply(int b) {
        return new Point2DInteger(x*b, y*b);
    }

    public Point2DInteger multiply(Point2DInteger b) {
        return new Point2DInteger(x+b.x, y+b.y);
    }

    public Point2DInteger divide(int b) {
        return new Point2DInteger(x/b, y/b);
    }

    public Point2DInteger divide(Point2DInteger b) {
        return new Point2DInteger(x/b.x, y/b.y);
    }

    public Point2DInteger multiplyEquals(int b) {
        x *= b;
        y *= b;
        return this;
    }

    public Point2DInteger multiplyEquals(Point2DInteger b) {
        x *= b.x;
        y *= b.y;
        return this;
    }

    public Point2DInteger divideEquals(int b) {
        x /= b;
        y /= b;
        return this;
    }

    public Point2DInteger divideEquals(Point2DInteger b) {
        x /= b.x;
        y /= b.y;
        return this;
    }

    public Point2DInteger minusEquals(int b) {
        x -= b;
        y -= b;
        return this;
    }

    public Point2DInteger minusEquals(Point2DInteger b) {
        x -= b.x;
        y -= b.y;
        return this;
    }

    public Point2DInteger plusEquals(int b) {
        x += b;
        y += b;
        return this;
    }

    public Point2DInteger plusEquals(Point2DInteger b) {
        x += b.x;
        y += b.y;
        return this;
    }

    public boolean lessThan(int b) {
        return length() < b;
    }

    public boolean greaterThan(int b) {
        return length() > b;
    }

    public boolean lessThan(Point2DInteger b) {
        return length() < b.length();
    }

    public boolean greaterThan(Point2DInteger b) {
        return length() > b.length();
    }

    public Point2DInteger normalize() {
        return this.divide((int)length());
    }

    public void load(DataInputStream dis) throws IOException {
        x = dis.readInt();
        y = dis.readInt();
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeInt(x);
        dos.writeInt(y);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars(x + " " + y + " ");
    }

}
