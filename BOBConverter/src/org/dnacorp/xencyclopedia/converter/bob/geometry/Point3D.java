package org.dnacorp.xencyclopedia.converter.bob.geometry;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:21.
 */
public class Point3D {

    private double x;
    private double y;
    private double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }

    public boolean equals(Point3D other) {
        return x == other.x && y == other.y && z == other.z;
    }

    public boolean notEquals(Point3D other) {
        return !equals(other);
    }

    public Point3D minus(double b) {
        return new Point3D(x-b, y-b, z-b);
    }

    public Point3D minus(Point3D b) {
        return new Point3D(x-b.x, y-b.y, z-b.z);
    }

    public Point3D plus(double b) {
        return new Point3D(x+b, y+b, z+b);
    }

    public Point3D plus(Point3D b) {
        return new Point3D(x+b.x, y+b.y, z+b.z);
    }

    public Point3D multiply(double b) {
        return new Point3D(x*b, y*b, z*b);
    }

    public Point3D multiply(Point3D b) {
        return new Point3D(x+b.x, y+b.y, z+b.z);
    }

    public Point3D divide(double b) {
        return new Point3D(x/b, y/b, z/b);
    }

    public Point3D divide(Point3D b) {
        return new Point3D(x/b.x, y/b.y, z/b.z);
    }

    public Point3D multiplyEquals(double b) {
        x *= b;
        y *= b;
        z *= b;
        return this;
    }

    public Point3D multiplyEquals(Point3D b) {
        x *= b.x;
        y *= b.y;
        z *= b.z;
        return this;
    }

    public Point3D divideEquals(double b) {
        x /= b;
        y /= b;
        z /= b;
        return this;
    }

    public Point3D divideEquals(Point3D b) {
        x /= b.x;
        y /= b.y;
        z /= b.z;
        return this;
    }

    public Point3D minusEquals(double b) {
        x -= b;
        y -= b;
        z -= b;
        return this;
    }

    public Point3D minusEquals(Point3D b) {
        x -= b.x;
        y -= b.y;
        z -= b.z;
        return this;
    }

    public Point3D plusEquals(double b) {
        x += b;
        y += b;
        z += b;
        return this;
    }

    public Point3D plusEquals(Point3D b) {
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

    public boolean lessThan(Point3D b) {
        return length() < b.length();
    }

    public boolean greaterThan(Point3D b) {
        return length() > b.length();
    }

    public Point3D normalize() {
        return this.divide(length());
    }

    public double dot(Point3D b) {
        return x * b.x + y * b.y + z * b.z;
    }

    public double angle(Point3D b) {
        return Math.acos(normalize().dot(b.normalize()));
    }

    public Point3D cross(Point3D b) {
        return new Point3D(y * b.z - z * b.y, z * b.x - x * b.z, x * b.y - y * b.x);
    }

}
