package xbjopengl.xun.com.xbjopengl.opengl.shape;

import xbjopengl.xun.com.xbjopengl.opengl.Mesh;

/**
 * @author xunwang
 * 
 *         2015-5-18
 */
public class SixCone extends Mesh {
	public float vertices1[];
	public float colors1[];
	public SixCone(float width, float height, float depth) {
		width /= 2;
		height /= 2;
		depth /= 2;

		float vertices[] = { 0, height, 0, // 0
				-width, 0, 0, // 1
				-width / 2, 0, depth, // 2
				width / 2, 0, depth, // 3
				width, 0, 0, // 4
				width / 2, 0, -depth, // 5
				-width / 2, 0, -depth, // 6

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
		vertices1 = vertices;
		colors1 = colors;
	}
	
	public float[] getSixConeVertices(){
		return vertices1;
	}
	
	public float[] getSixConeColors(){
		return colors1;
	}
}
