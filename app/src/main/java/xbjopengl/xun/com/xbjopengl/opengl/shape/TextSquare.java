package xbjopengl.xun.com.xbjopengl.opengl.shape;


import xbjopengl.xun.com.xbjopengl.opengl.Mesh;

/**
 * @author xunwang
 * 
 *         2015-5-22
 */
public class TextSquare extends Mesh {
	public TextSquare() {
		this(1, 1);
	}

	public TextSquare(float width, float height) {
		// Mapping coordinates for the vertices
		float textureCoordinates[] = { 0.0f, 1.0f, //
				1.0f, 1.0f, //
				0.0f, 0.0f, //
				1.0f, 0.0f, //
		};

		// The colors mapped to the vertices.
		float[] colors = { 1f, 1f, 1f, 1f, // vertex 0 red
				1f, 1f, 1f, 1f, // vertex 1 green
				1f, 1f, 1f, 1f, // vertex 2 blue
				1f, 1f, 1f, 1f, // vertex 3 magenta
		};

		short[] indices = new short[] { 0, 1, 2, 1, 3, 2 };

		float[] vertices = new float[] { -0.5f, -0.125f, 0.0f, 0.5f, -0.125f, 0.0f, -0.5f, 0.125f, 0.0f, 0.5f, 0.125f, 0.0f };

		setIndices(indices);
		setVertices(vertices);
		setTextureCoordinates(textureCoordinates);
		setColors(colors);
	}
}
