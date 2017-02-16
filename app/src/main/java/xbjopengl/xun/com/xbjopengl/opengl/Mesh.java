package xbjopengl.xun.com.xbjopengl.opengl;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * @author xunwang
 * 
 *         2015-5-22
 */
public class Mesh {
	// Our vertex buffer.
	private FloatBuffer verticesBuffer = null;

	// Our index buffer.
	private ShortBuffer indicesBuffer = null;

	// Our UV texture buffer.
	private FloatBuffer mTextureBuffer; // New variable.

	// The number of indices.
	private int numOfIndices = -1;

	// Our texture id.
	private int mTextureId = -1; // New variable.

	// The bitmap we want to load as a texture.
	private Bitmap mBitmap; // New variable.

	// Indicates if we need to load the texture.
	private boolean mShouldLoadTexture = false; // New variable.

	// Flat Color
	private float[] rgba = new float[] { 1.0f, 1.0f, 1.0f, 1.0f };

	// Smooth Colors
	private FloatBuffer colorBuffer = null;

	// Translate params.
	public float x = 0;

	public float y = 0;

	public float z = 0;

	// Rotate params.
	public float rx = 0;

	public float ry = 0;

	public float rz = 0;

	private boolean flag;//标识填充类型的flag，线填充或面填充

	public void draw(GL10 gl) {
		// Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CCW);
		// Enable face culling.
		gl.glEnable(GL10.GL_CULL_FACE);
		// What faces to remove with the face culling.
		gl.glCullFace(GL10.GL_BACK);
		// Enabled the vertices buffer for writing and
		// to be used during
		// rendering.
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// Specifies the location and data format
		// of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
		// Set flat color
		gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);
		// Smooth color
		if (colorBuffer != null) {
			// Enable the color array buffer to be
			// used during rendering.
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		}

		// New part...
		if (mShouldLoadTexture) {
			loadGLTexture(gl);
			mShouldLoadTexture = false;
		}
		if (mTextureId != -1 && mTextureBuffer != null) {
			gl.glEnable(GL10.GL_TEXTURE_2D);
			// Enable the texture state
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

			// Point to our buffers
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
			gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);
		}
		// ... end new part.

		gl.glTranslatef(x, y, z);
		gl.glRotatef(rx, 1, 0, 0);
		gl.glRotatef(ry, 0, 1, 0);
		gl.glRotatef(rz, 0, 0, 1);

		if (flag) {
			// gl.glEnable(GL10.GL_TEXTURE_2D);
			gl.glDrawElements(GL10.GL_TRIANGLES, numOfIndices, GL10.GL_UNSIGNED_SHORT, indicesBuffer);

		} else {
			// Point out the where the color buffer is.
			gl.glLineWidth(1);
			gl.glDisable(GL10.GL_TEXTURE_2D);
			gl.glDrawElements(GL10.GL_LINE_LOOP, numOfIndices, GL10.GL_UNSIGNED_SHORT, indicesBuffer);
		}

		// Disable the vertices buffer.
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		// New part...
		if (mTextureId != -1 && mTextureBuffer != null) {
			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		}
		// ... end new part.
		// Disable face culling.
		gl.glDisable(GL10.GL_CULL_FACE);
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	protected void setVertices(float[] vertices) {
		// a float is 4 bytes, therefore
		// we multiply the number if
		// vertices with 4.
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		verticesBuffer = vbb.asFloatBuffer();
		verticesBuffer.put(vertices);
		verticesBuffer.position(0);
	}

	public void setTextVertices(int textCount, int textSize) {
		// The colors mapped to the vertices.
		// 20 4
		float[] vertices = { -0.25f * textCount, -0.125f / 20 * textSize, 0.0f, //
				0.25f * textCount, -0.125f / 20 * textSize, 0.0f,//
				-0.25f * textCount, 0.125f / 20 * textSize, 0.0f,//
				0.25f * textCount, 0.125f / 20 * textSize, 0.0f };//
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		verticesBuffer = vbb.asFloatBuffer();
		verticesBuffer.put(vertices);
		verticesBuffer.position(0);
	}

	protected void setIndices(short[] indices) {
		// short is 2 bytes, therefore we multiply
		// the number if
		// vertices with 2.
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indicesBuffer = ibb.asShortBuffer();
		indicesBuffer.put(indices);
		indicesBuffer.position(0);
		numOfIndices = indices.length;
	}

	/**
	 * Set the texture coordinates.
	 * 
	 * @param textureCoords
	 */
	protected void setTextureCoordinates(float[] textureCoords) { // New
																	// function.
		// float is 4 bytes, therefore we multiply the number if
		// vertices with 4.
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoords.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		mTextureBuffer = byteBuf.asFloatBuffer();
		mTextureBuffer.put(textureCoords);
		mTextureBuffer.position(0);
	}

	protected void setColor(float red, float green, float blue, float alpha) {
		// Setting the flat color.
		rgba[0] = red;
		rgba[1] = green;
		rgba[2] = blue;
		rgba[3] = alpha;
	}

	protected void setColors(float[] colors) {
		// float has 4 bytes.
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
	}

	// set font alpha
	public void setFontAlpha(float alpha) {
		// The colors mapped to the vertices.
		float[] colors = { 1f, 1f, 1f, alpha, // vertex 0 red
				1f, 1f, 1f, alpha, // vertex 1 green
				1f, 1f, 1f, alpha, // vertex 2 blue
				1f, 1f, 1f, alpha, // vertex 3 magenta
		};
		// float has 4 bytes.
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
	}

	public void setHexachedronAlpha(float[] alpha, float[] cubeColors) {
		// The colors mapped to the vertices.
		if (alpha != null && alpha.length >= 12) {
			float[] colors = { cubeColors[0], cubeColors[1], cubeColors[2], alpha[0], // vertex 0
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[1], // vertex 1
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[2], // vertex 2
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[3], // vertex 3
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[4], // vertex 4
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[5], // vertex 5
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[6], // vertex 0
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[7], // vertex 1
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[8], // vertex 2
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[9], // vertex 3
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[10], // vertex 4
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[11], // vertex 5
			};
			// float has 4 bytes.
			ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
			cbb.order(ByteOrder.nativeOrder());
			colorBuffer = cbb.asFloatBuffer();
			colorBuffer.put(colors);
			colorBuffer.position(0);
		}
	}

	public void setSixConeAlpha(float[] alpha, float[] cubeColors) {
		// The colors mapped to the vertices.
		if (alpha != null && alpha.length >= 6) {
			float[] colors = { cubeColors[0], cubeColors[1], cubeColors[2], 1f, // vertex 0
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[0], // vertex 1
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[1], // vertex 2
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[2], // vertex 3
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[3], // vertex 4
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[4], // vertex 5
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[5],// vertex 6
			};
			// float has 4 bytes.
			ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
			cbb.order(ByteOrder.nativeOrder());
			colorBuffer = cbb.asFloatBuffer();
			colorBuffer.put(colors);
			colorBuffer.position(0);
		}
	}

	public void setAntiSixConeAlpha(float[] alpha, float[] cubeColors) {
		if (alpha != null && alpha.length >= 6) {
			// The colors mapped to the vertices.
			float[] colors = { cubeColors[0], cubeColors[1], cubeColors[2], 1f, // vertex 0
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[0], // vertex 1
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[1], // vertex 2
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[2], // vertex 3
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[3], // vertex 4
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[4], // vertex 5
					cubeColors[0], cubeColors[1], cubeColors[2], alpha[5], // vertex 5
			};
			// float has 4 bytes.
			ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
			cbb.order(ByteOrder.nativeOrder());
			colorBuffer = cbb.asFloatBuffer();
			colorBuffer.put(colors);
			colorBuffer.position(0);
		}
	}

	/**
	 * Set the bitmap to load into a texture.
	 * 
	 * @param bitmap
	 */
	public void loadBitmap(Bitmap bitmap) { // New function.
		this.mBitmap = bitmap;
		mShouldLoadTexture = true;
	}

	/**
	 * Loads the texture.
	 * 
	 * @param gl
	 */
	private void loadGLTexture(GL10 gl) { // New function
		// Generate one texture pointer...
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		mTextureId = textures[0];

		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);

		// Create Nearest Filtered Texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		// Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

		// Use the Android GLUtils to specify a two-dimensional texture image
		// from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap, 0);
	}

}
