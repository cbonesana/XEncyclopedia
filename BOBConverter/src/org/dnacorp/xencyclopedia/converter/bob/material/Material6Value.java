package org.dnacorp.xencyclopedia.converter.bob.material;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 18:02.
 */
public class Material6Value {

    private static List<String> m_stringTypes = new ArrayList<>();

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
        return type < m_stringTypes.size() ? m_stringTypes.get(type) : "";
    }

    public String typeName() {
        return typeName(type.v);
    }

    public static int typeNameCount() {
        return m_stringTypes.size();
    }

    public void load(FileInputStream fis) throws IOException {
        // TODO
    }

    public void toBinaryFile(FileOutputStream fos) throws IOException {
        // TODO
    }

    public void toPlainFile(FileOutputStream fos) throws IOException {
        // TODO
    }
}
