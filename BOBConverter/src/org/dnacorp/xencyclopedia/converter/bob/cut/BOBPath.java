package org.dnacorp.xencyclopedia.converter.bob.cut;

import org.dnacorp.xencyclopedia.converter.bob.dom.BOBDomBOB;
import org.dnacorp.xencyclopedia.converter.bob.BOBNames;
import org.dnacorp.xencyclopedia.converter.bob.BOBSection;
import org.dnacorp.xencyclopedia.converter.bob.Settings;
import org.dnacorp.xencyclopedia.converter.bob.frame.BOBFrame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 10.09.2014 22:14.
 */
public class BOBPath extends BOBSection {

    public enum BodyFlags {
        fCamera(1),
        fDirLight(2),
        fOmniLight(10),
        fBody(64),
        fScene(128);

        int value;
        BodyFlags(int v){
            this.value = v;
        }
    }

    private static final int HDR_STAT_BEGIN = BOBNames.BOB_SECTION_NAME_STAT_BEGIN;
    private static final int HDR_STAT_END   = BOBNames.BOB_SECTION_NAME_STAT_END;

    public static final int HDR_BEGIN = BOBNames.BOB_SECTION_NAME_PATH_BEGIN;
    public static final int HDR_END   = BOBNames.BOB_SECTION_NAME_PATH_END;

    private List<BOBFrame> frameContainer = new ArrayList<>();
    private List<Integer>  tempStatList   = new ArrayList<>();
    private List<BOBFrame> children       = new ArrayList<>();

    private BOBNotes m_notes      = new BOBNotes();
    private Settings settings     = new Settings();
    private String m_bodyId       = "";

    public BOBName name           = new BOBName();
    public BOBConstants constants = new BOBConstants() ;

    public int partIdx;
    public int cockpitIdx;
    public int parentIdx;
    int bodyFlags;  // 'c' for cameras, 'l' for lights

    public BOBDomBOB bob;

    public BOBPath(Settings settings) {
        this.settings = settings;
    }

    public String bodyId() {
        return m_bodyId;
    }

    public BOBNotes notes() {
        return m_notes;
    }

    private void loadStatValues(DataInputStream dis, int count) {
        // TODO
    }

    public void load(DataInputStream dis, int version) {
        // TODO
    }

    public void toBinaryFile(DataOutputStream dos, int cutVersion) {
        // TODO
    }

    public void toTextFile(DataOutputStream dos, int idx) {
        // TODO
    }

    public BOBFrame createChild() {
        BOBFrame ch = new BOBFrame();
        children.add(ch);
        return ch;
    }
}
