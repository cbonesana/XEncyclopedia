package org.dnacorp.xencyclopedia.converter.bob.point;

import org.dnacorp.xencyclopedia.converter.bob.vertex.BOBVertex;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 21:12.
 */
public class PointRec {

    private PPNode m_node;

    public PointRec(PPNode node) {
        m_node = node;
    }

    public PointRec(PointRec other) {
        m_node = other.m_node;
    }

    public BOBVertex get() {
        return m_node.pnt;
    }

}
