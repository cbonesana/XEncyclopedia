package org.dnacorp.xencyclopedia.converter.bob.vertex;

import org.dnacorp.xencyclopedia.converter.bob.geometry.Point3DInteger;

/**
 * Created by Claudio "Dna" Bonesana
 * Date: 09.09.2014 20:47.
 */
public class Position3D extends Vertex {

    public Position3D() {
        super();
    }

    public Position3D(int x, int y, int z) {
        super(x, y, z);
    }

    public Position3D(Point3DInteger pt) {
        super(pt);
    }

}
