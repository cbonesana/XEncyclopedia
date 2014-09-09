package org.dnacorp.xencyclopedia.converter.bob.point;

import org.dnacorp.xencyclopedia.converter.bob.vertex.BOBVertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 08.09.2014 21:16.
 */
public class BOBPointMap {

    private PNode m_root;
    private int m_compCount = 0;
    private int m_unique_it = 0;

    private List<BOBVertex> m_points;
    private List<Integer> m_indexes;
    private List<Integer> m_uniqueIndices = new ArrayList<>();

    public PNode addNode(PNode parent, char id) {
        PNode n, old = null;

        n=parent.child;
        while (n != null) {
            m_compCount++;
            if (n.id == id)
                return n;
            old = n;
            n = n.next;
        }

        if (parent.child == null)
            n = parent.child = new PNode();
        else
            n = old.next = new PNode();
        n.id = id;
        return n;
    }

    public PPNode addPPNode(PNode parent) {
        PNode n, old = null;
        PPNode pp;

        n=parent.child;
        while (n != null) {
            old = n;
            n = n.next;
        }

        if (parent.child == null)
            parent.child = pp = new PPNode();
        else if (old != null)
            old.next = pp = new PPNode();
        else
            pp = new PPNode();
        return pp;
    }

    public PNode findNode(PNode parent, char id) {
        PNode ch;
        for (ch = parent.child; ch != null; ch = ch.next)
            if (ch.id == id)
                return ch;
        return null;
    }

    public void deleteChilds(PNode parent) {
        // TODO: really delete something...
        PNode ch;
        for (ch = parent.child; ch != null; ch = ch.next)
            deleteChilds(ch);
    }

    private PointBucket _addPoint(BOBVertex pnt) {
        PNode n;
        int v;
        v = pnt.x;
        n = m_root;
        char id;
        for (int i=3; i >=0; i--) {
            id = (char)(v >> (i*8));
            n = addNode(n, id);
        }

        v = pnt.y;
        for (int i=3; i >=0; i--) {
            id = (char)(v >> (i*8));
            n = addNode(n, id);
        }

        v = pnt.z;
        for (int i=3; i >=0; i--) {
            id = (char)(v >> (i*8));
            n = addNode(n, id);
        }

        PPNode pp = addPPNode(n);
        pp.pnt = pnt;

        return new PointBucket((PPNode)n.child);
    }

    public void create(int size) {
        m_points  = new ArrayList<>();
        m_indexes = new ArrayList<>();
        m_compCount = 0;
    }

    public void addPoint(BOBVertex pnt) {
        PointBucket pb = _addPoint(pnt);

        if (pb.size() == 1) {
            pb.index(m_uniqueIndices.size());
            m_uniqueIndices.add(m_points.size());
        }
    }

    public void clear() {
        deleteChilds(m_root);
        m_root.child = null;

        m_points.clear();
        m_indexes.clear();
        m_points.clear();
    }

    public BOBVertex get(int idx) {
        return m_points.get(idx);
    }

    public int uniqueIndex(int idx) {
        return idx < m_points.size() ? m_indexes.get(idx) : -1;
    }

    public int pointsSize() {
        return m_points.size();
    }

    public int uniquePointsSize() {
        return m_uniqueIndices.size();
    }

    public BOBVertex nextUniquePoint() {
        // TODO: check these iterators
        BOBVertex p = (m_unique_it != m_uniqueIndices.size() ? m_points.get(m_unique_it) : null);
        m_unique_it++;
        return p;
    }

}
