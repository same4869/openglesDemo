package xbjopengl.xun.com.xbjopengl.opengl.shape;


import xbjopengl.xun.com.xbjopengl.opengl.Mesh;

/**
 * @author xunwang
 * 
 *         2015-5-18
 */
public class Hexahedron extends Mesh {
	public Hexahedron(float width, float height, float depth) {
		width /= 2;
		height /= 2;
		depth /= 2;

		float vertices[] = { -width, 0, 0, // 0
				-width / 2, 0, depth, // 1
				width / 2, 0, depth, // 2
				width, 0, 0, // 3
				width / 2, 0, -depth, // 4
				-width / 2, 0, -depth, // 5
				(float) (-width * Math.sqrt(3) / 2), -height, -depth / 2, // 6
				(float) (-width * Math.sqrt(3) / 2), -height, depth / 2, // 7
				0, -height, depth, // 8
				(float) (width * Math.sqrt(3) / 2), -height, depth / 2, // 9
				(float) (width * Math.sqrt(3) / 2), -height, -depth / 2, // 10
				0, -height, -depth, // 11

		};
		
        // The colors mapped to the vertices.
        float[] colors = { 0.77f, 0.95f, 0.97f, 1f, // vertex 0
        		0.77f, 0.95f, 0.97f, 1f, // vertex 1
        		0.77f, 0.95f, 0.97f, 1f, // vertex 2
        		0.77f, 0.95f, 0.97f, 1f, // vertex 3
        		0.77f, 0.95f, 0.97f, 1f, // vertex 4
        		0.77f, 0.95f, 0.97f, 1f, // vertex 5
        		0.77f, 0.95f, 0.97f, 1f, // vertex 0
        		0.77f, 0.95f, 0.97f, 1f, // vertex 1
        		0.77f, 0.95f, 0.97f, 1f, // vertex 2
        		0.77f, 0.95f, 0.97f, 1f, // vertex 3
        		0.77f, 0.95f, 0.97f, 1f, // vertex 4
        		0.77f, 0.95f, 0.97f, 1f, // vertex 5
        };

		short indices[] = { 0, 6, 7, 7, 0, 1, 1, 7, 8, 8, 1, 2, 2, 8, 9, 9, 2, 3, 3, 9, 10, 10, 3, 4, 4, 10, 11, 11, 4, 5, 5, 11, 6, 6, 5, 0 };

		setIndices(indices);
		setVertices(vertices);
		setColors(colors);
	}
}
