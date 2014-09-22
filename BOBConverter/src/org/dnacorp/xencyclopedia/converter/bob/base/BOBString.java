package org.dnacorp.xencyclopedia.converter.bob.base;

import org.dnacorp.xencyclopedia.converter.bob.BOBSection;

import java.io.*;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 21:26.
 */
public class BOBString extends BOBSection {

    public String m_text = "";

    public String text() {
        return m_text;
    }

    public void text(String str) {
        m_text = str;
    }

    public boolean load(DataInputStream dis, int startHeader, int endHeader) throws IOException {
        int hdr = dis.readInt();
        if (hdr != startHeader) {
            error(BOBErrorCodes.e_badHeader);
            return false;
        }

        StringBuilder sb = new StringBuilder();
        char ch;
        while ((ch = dis.readChar()) != 0x00) {
            sb.append(ch);
        }
        m_text = sb.toString();
        if (m_text.length() == 0) {
            error(BOBErrorCodes.e_notEnoughData);
            return false;
        }

        hdr = dis.readInt();
        if (hdr != endHeader)
            error(BOBErrorCodes.e_badEndHeader);
        return hdr == endHeader;
    }

    public void toBinaryFile(DataOutputStream dos, int begin, int end) throws IOException {
        if (m_text != null && m_text.length() != 0) {
            dos.writeInt(begin);
            dos.writeChars(m_text);
            dos.writeInt(end);
        }
    }

    public void toTextFile(DataOutputStream dos) throws IOException {
        if (m_text != null && m_text.length() != 0)
            dos.writeChars("/#" + m_text + "\n");
    }

}
