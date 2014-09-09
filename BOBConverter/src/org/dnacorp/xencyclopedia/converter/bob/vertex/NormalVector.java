package org.dnacorp.xencyclopedia.converter.bob.vertex;

import org.dnacorp.xencyclopedia.converter.bob.geometry.Point3DDouble;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 20:46.
 */
public class NormalVector extends Vector {

    public NormalVector(double x, double y, double z) {
        super(x, y, z);
    }

    public NormalVector(Point3DDouble pt) {
        super(pt);
    }
}
