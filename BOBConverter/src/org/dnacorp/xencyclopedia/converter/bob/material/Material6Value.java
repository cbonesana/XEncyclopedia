package org.dnacorp.xencyclopedia.converter.bob.material;

import org.dnacorp.xencyclopedia.converter.bob.base.BOBErrorCodes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:02.
 */
public class Material6Value {

    private static String[] m_stringTypes = {
            "SPTYPE_LONG",
            "SPTYPE_BOOL",
            "SPTYPE_FLOAT",
            "",
            "",
            "SPTYPE_FLOAT4",
            "",
            "",
            "SPTYPE_STRING"
    };

    public enum Type {
        typeLong(0),
        typeBool(1),
        typeFloat(2),
        typeFloat4(5),
        typeString(8);

        private int v = 0;

        Type(int v) {
            this.v = v;
        }
    }

    public Type type;
    public String name;
    public TagValue val;

    public Material6Value() {
        val.i = 0;
        type = Type.typeLong;
    }

    public Material6Value(Type type) {
        val.i = 0;
        this.type = type;
    }

    public static String typeName(int type) {
        return type < m_stringTypes.length ? m_stringTypes[type] : "";
    }

    public String typeName() {
        return typeName(type.v);
    }

    public static int typeNameCount() {
        return m_stringTypes.length;
    }

    public BOBErrorCodes load(DataInputStream dis) throws IOException {
        name = dis.readUTF();
        short t = dis.readShort();
        type = Type.values()[t];

        switch (type){
            case typeBool:
            case typeLong: val.i = dis.readInt(); break;
            case typeFloat: val.f = dis.readFloat(); break;
            case typeFloat4: val.f4.load(dis); break;
            case typeString: val.psz = dis.readUTF();
            default: return BOBErrorCodes.e_unkMaterialValueType;
        }

        return BOBErrorCodes.e_noError;
    }

    public boolean toBinaryFile(DataOutputStream dos) throws IOException {
        dos.writeChars(name);

        switch (type) {
            case typeBool:
            case typeLong: dos.write(val.i); break;
            case typeFloat: dos.writeFloat(val.f); break;
            case typeFloat4: val.f4.toBinaryFile(dos); break;
            case typeString: dos.writeChars(val.psz); break;
            default: return false;
        }
        return true;
    }

    public boolean toTextFile(DataOutputStream dos) throws IOException {
        dos.writeChars(" " + name + ";" + typeName() + ";");

        switch (type) {
            case typeBool:
            case typeLong: dos.write(val.i); dos.writeChar(';'); break;
            case typeFloat: dos.writeFloat(val.f); dos.writeChar(';'); break;
            case typeFloat4: val.f4.toTextFile(dos); dos.writeChar(';'); break;
            case typeString: dos.writeChars(val.psz); dos.writeChar(';'); break;
            default: return false;
        }
        dos.writeChar(' ');
        return true;
    }
}
