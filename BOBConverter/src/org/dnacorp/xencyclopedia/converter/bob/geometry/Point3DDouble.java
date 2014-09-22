package org.dnacorp.xencyclopedia.converter.bob.geometry;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:21.
 */
public class Point3DDouble {

    public double x;
    public double y;
    public double z;

    public Point3DDouble() {}

    public Point3DDouble(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3DDouble(Point3DDouble pt) {
        this.x = pt.x;
        this.y = pt.y;
        this.z = pt.z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }

    public boolean equals(Point3DDouble other) {
        return x == other.x && y == other.y && z == other.z;
    }

    public boolean notEquals(Point3DDouble other) {
        return !equals(other);
    }

    public Point3DDouble minus(double b) {
        return new Point3DDouble(x-b, y-b, z-b);
    }

    public Point3DDouble minus(Point3DDouble b) {
        return new Point3DDouble(x-b.x, y-b.y, z-b.z);
    }

    public Point3DDouble plus(double b) {
        return new Point3DDouble(x+b, y+b, z+b);
    }

    public Point3DDouble plus(Point3DDouble b) {
        return new Point3DDouble(x+b.x, y+b.y, z+b.z);
    }

    public Point3DDouble multiply(double b) {
        return new Point3DDouble(x*b, y*b, z*b);
    }

    public Point3DDouble multiply(Point3DDouble b) {
        return new Point3DDouble(x+b.x, y+b.y, z+b.z);
    }

    public Point3DDouble divide(double b) {
        return new Point3DDouble(x/b, y/b, z/b);
    }

    public Point3DDouble divide(Point3DDouble b) {
        return new Point3DDouble(x/b.x, y/b.y, z/b.z);
    }

    public Point3DDouble multiplyEquals(double b) {
        x *= b;
        y *= b;
        z *= b;
        return this;
    }

    public Point3DDouble multiplyEquals(Point3DDouble b) {
        x *= b.x;
        y *= b.y;
        z *= b.z;
        return this;
    }

    public Point3DDouble divideEquals(double b) {
        x /= b;
        y /= b;
        z /= b;
        return this;
    }

    public Point3DDouble divideEquals(Point3DDouble b) {
        x /= b.x;
        y /= b.y;
        z /= b.z;
        return this;
    }

    public Point3DDouble minusEquals(double b) {
        x -= b;
        y -= b;
        z -= b;
        return this;
    }

    public Point3DDouble minusEquals(Point3DDouble b) {
        x -= b.x;
        y -= b.y;
        z -= b.z;
        return this;
    }

    public Point3DDouble plusEquals(double b) {
        x += b;
        y += b;
        z += b;
        return this;
    }

    public Point3DDouble plusEquals(Point3DDouble b) {
        x += b.x;
        y += b.y;
        z += b.z;
        return this;
    }

    public boolean lessThan(double b) {
        return length() < b;
    }

    public boolean greaterThan(double b) {
        return length() > b;
    }

    public boolean lessThan(Point3DDouble b) {
        return length() < b.length();
    }

    public boolean greaterThan(Point3DDouble b) {
        return length() > b.length();
    }

    public Point3DDouble normalize() {
        return this.divide(length());
    }

    public double dot(Point3DDouble b) {
        return x * b.x + y * b.y + z * b.z;
    }

    public double angle(Point3DDouble b) {
        return Math.acos(normalize().dot(b.normalize()));
    }

    public Point3DDouble cross(Point3DDouble b) {
        return new Point3DDouble(y * b.z - z * b.y, z * b.x - x * b.z, x * b.y - y * b.x);
    }

    public void load(DataInputStream dis) throws IOException {
        x = dis.readDouble();
        y = dis.readDouble();
        z = dis.readDouble();
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeDouble(x);
        dos.writeDouble(y);
        dos.writeDouble(z);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars(x + " " + y + " " + z + " ");
    }

}
