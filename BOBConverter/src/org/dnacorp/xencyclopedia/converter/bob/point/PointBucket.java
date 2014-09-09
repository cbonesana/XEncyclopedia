package org.dnacorp.xencyclopedia.converter.bob.point;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 21:14.
 */
public class PointBucket {

    private int m_size;
    private PPNode m_first;

    public PointBucket(PPNode first) {
        m_first = first;
        m_size = 0;
        for (PNode n = m_first; n != null; n = n.next)
            m_size++;
    }

    public PointBucket(PointBucket other) {
        m_size = other.m_size;
        m_first = other.m_first;
    }

    public int size() {
        return m_size;
    }

    public int index() {
        return  m_first.index;
    }

    public void index(int idx) {
        m_first.index = idx;
    }

    PointRec get(int idx) {
        int i=0;
        PNode n;
        n=m_first;
        while (i < idx) {
            i++;
            n = n.next;
        }
        return new PointRec((PPNode)n);
    }

}
