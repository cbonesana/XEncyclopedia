package org.dnacorp.xencyclopedia.converter.bob.geometry;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:21.
 */
public class Point3DInteger {

    public int x;
    public int y;
    public int z;

    public Point3DInteger(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3DInteger(Point3DInteger pt) {
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

    public boolean equals(Point3DInteger other) {
        return x == other.x && y == other.y && z == other.z;
    }

    public boolean notEquals(Point3DInteger other) {
        return !equals(other);
    }

    public Point3DInteger minus(int b) {
        return new Point3DInteger(x-b, y-b, z-b);
    }

    public Point3DInteger minus(Point3DInteger b) {
        return new Point3DInteger(x-b.x, y-b.y, z-b.z);
    }

    public Point3DInteger plus(int b) {
        return new Point3DInteger(x+b, y+b, z+b);
    }

    public Point3DInteger plus(Point3DInteger b) {
        return new Point3DInteger(x+b.x, y+b.y, z+b.z);
    }

    public Point3DInteger multiply(int b) {
        return new Point3DInteger(x*b, y*b, z*b);
    }

    public Point3DInteger multiply(Point3DInteger b) {
        return new Point3DInteger(x+b.x, y+b.y, z+b.z);
    }

    public Point3DInteger divide(int b) {
        return new Point3DInteger(x/b, y/b, z/b);
    }

    public Point3DInteger divide(Point3DInteger b) {
        return new Point3DInteger(x/b.x, y/b.y, z/b.z);
    }

    public Point3DInteger multiplyEquals(int b) {
        x *= b;
        y *= b;
        z *= b;
        return this;
    }

    public Point3DInteger multiplyEquals(Point3DInteger b) {
        x *= b.x;
        y *= b.y;
        z *= b.z;
        return this;
    }

    public Point3DInteger divideEquals(int b) {
        x /= b;
        y /= b;
        z /= b;
        return this;
    }

    public Point3DInteger divideEquals(Point3DInteger b) {
        x /= b.x;
        y /= b.y;
        z /= b.z;
        return this;
    }

    public Point3DInteger minusEquals(int b) {
        x -= b;
        y -= b;
        z -= b;
        return this;
    }

    public Point3DInteger minusEquals(Point3DInteger b) {
        x -= b.x;
        y -= b.y;
        z -= b.z;
        return this;
    }

    public Point3DInteger plusEquals(int b) {
        x += b;
        y += b;
        z += b;
        return this;
    }

    public Point3DInteger plusEquals(Point3DInteger b) {
        x += b.x;
        y += b.y;
        z += b.z;
        return this;
    }

    public boolean lessThan(int b) {
        return length() < b;
    }

    public boolean greaterThan(int b) {
        return length() > b;
    }

    public boolean lessThan(Point3DInteger b) {
        return length() < b.length();
    }

    public boolean greaterThan(Point3DInteger b) {
        return length() > b.length();
    }

    public Point3DInteger normalize() {
        return this.divide((int)length());
    }

    public int dot(Point3DInteger b) {
        return x * b.x + y * b.y + z * b.z;
    }

    public double angle(Point3DInteger b) {
        return Math.acos(normalize().dot(b.normalize()));
    }

    public Point3DInteger cross(Point3DInteger b) {
        return new Point3DInteger(y * b.z - z * b.y, z * b.x - x * b.z, x * b.y - y * b.x);
    }

    public boolean load(DataInputStream dis) throws IOException {
        x = dis.readInt();
        y = dis.readInt();
        z = dis.readInt();
        return false;
    }

    public void toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeInt(x);
        dos.writeInt(y);
        dos.writeInt(z);
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        dos.writeInt(x);
        dos.writeChar(' ');
        dos.writeInt(y);
        dos.writeChar(' ');
        dos.writeInt(z);
        dos.writeChar(' ');
    }

}
