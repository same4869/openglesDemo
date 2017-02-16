package xbjopengl.xun.com.xbjopengl.opengl.shape;


import xbjopengl.xun.com.xbjopengl.opengl.Mesh;

/**
 * @author xunwang
 * 
 *         2015-5-18
 */
public class AntiSixCone extends Mesh {
	public AntiSixCone(float width, float height, float depth) {
		width /= 2;
		height /= 2;
		depth /= 2;

		float vertices[] = { 0, -height - 2, 0, // 0
				(float) (-width * Math.sqrt(3) / 2), -2, -depth / 2, // 1
				(float) (-width * Math.sqrt(3) / 2), -2, depth / 2, // 2
				0, -2, depth, // 3
				(float) (width * Math.sqrt(3) / 2), -2, depth / 2, // 4
				(float) (width * Math.sqrt(3) / 2), -2, -depth / 2, // 5
				0, -2, -depth, // 6

		};

		// The colors mapped to the vertices.
		float[] colors = { 0.77f, 0.95f, 0.97f, 1f, // vertex 0
				0.77f, 0.95f, 0.97f, 1f, // vertex 1
				0.77f, 0.95f, 0.97f, 1f, // vertex 2
				0.77f, 0.95f, 0.97f, 1f, // vertex 3
				0.77f, 0.95f, 0.97f, 1f, // vertex 4
				0.77f, 0.95f, 0.97f, 1f, // vertex 5
		};

		short indices[] = { 0, 1, 2, 0, 2, 3, 0, 3, 4, 0, 4, 5, 0, 5, 6, 0, 6, 1 };

		setIndices(indices);
		setVertices(vertices);
		setColors(colors);
	}
}
