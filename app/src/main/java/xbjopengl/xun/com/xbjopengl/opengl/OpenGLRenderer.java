package xbjopengl.xun.com.xbjopengl.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import xbjopengl.xun.com.xbjopengl.ScreenUtils;
import xbjopengl.xun.com.xbjopengl.opengl.shape.AntiSixCone;
import xbjopengl.xun.com.xbjopengl.opengl.shape.Hexahedron;
import xbjopengl.xun.com.xbjopengl.opengl.shape.SixCone;
import xbjopengl.xun.com.xbjopengl.opengl.shape.TextSquare;

/**
 * @author wangxun
 * 
 *         2015-5-16
 */

public class OpenGLRenderer implements Renderer {
	private static final float CUBE_WIDTH = 3.3f;
	// Group cubeGroup;
	private TextSquare textSquare, textSquare1, textSquare2, textSquare3, textSquare4, textSquare5, textSquare6, textSquare7, textSquare8, textSquare9,
			textSquare10, textSquare11, textSquare12, textSquare13;
	private SixCone sixCone;
	private Hexahedron hexahedron;
	private AntiSixCone antiSixCone;

	private float[] hexachedronAlpha = new float[12];
	private float[] sixConeAlpha = new float[6];
	private float[] antiSixConeAlpha = new float[6];

	private int i;
	private int angle = 0;

	private boolean isShowFont;
	private float[] cubeColors = new float[3];
	private int themeColor;
	private float scale, scale2;// 适配不同屏幕

	public OpenGLRenderer(Context context, int themeColor) {
		this.themeColor = themeColor;
		scale = ScreenUtils.getStatusHeight(context);
		scale2 = ScreenUtils.getScreenDensity(context);
	}

	public void resetSchoolInfo() {
		initFont();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {

		// Set the background color to black ( rgba ).
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// Enable Smooth Shading, default not really needed.
		gl.glShadeModel(GL10.GL_SMOOTH);
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);
		// Enables depth testing.
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// The type of depth testing to do.
		gl.glDepthFunc(GL10.GL_LEQUAL);
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		// cubeGroup = new Group();
		sixCone = new SixCone(CUBE_WIDTH, 2f, CUBE_WIDTH);
		hexahedron = new Hexahedron(CUBE_WIDTH, 4f, CUBE_WIDTH);
		antiSixCone = new AntiSixCone(CUBE_WIDTH, 2f, CUBE_WIDTH);

		// 获取主题颜色
		String cubeColor = Integer.toHexString(themeColor);
		cubeColors[0] = Integer.valueOf((String) cubeColor.subSequence(2, 4), 16) / 255f;
		cubeColors[1] = Integer.valueOf((String) cubeColor.subSequence(4, 6), 16) / 255f;
		cubeColors[2] = Integer.valueOf((String) cubeColor.subSequence(6, 8), 16) / 255f;
		initFont();

	}

	private void initFont() {
		textSquare = new TextSquare();
		textSquare.loadBitmap(GLFont.getImage(128, 32, "宝安中学", 32));
		textSquare1 = new TextSquare();
		textSquare1.loadBitmap(GLFont.getImage(128, 32, "深圳中学", 32));
		textSquare2 = new TextSquare();
		textSquare2.loadBitmap(GLFont.getImage(128, 32, "柳河十中", 32));
		textSquare3 = new TextSquare();
		textSquare3.loadBitmap(GLFont.getImage(128, 32, "博雅学校", 32));
		textSquare4 = new TextSquare();
		textSquare4.loadBitmap(GLFont.getImage(128, 32, "安仁中学", 32));
		textSquare5 = new TextSquare();
		textSquare5.loadBitmap(GLFont.getImage(128, 32, "北京五中", 32));
		textSquare6 = new TextSquare();
		textSquare6.loadBitmap(GLFont.getImage(128, 32, "南开中学", 32));
		textSquare7 = new TextSquare();
		textSquare7.loadBitmap(GLFont.getImage(128, 32, "成都七中", 32));
		textSquare8 = new TextSquare();
		textSquare8.loadBitmap(GLFont.getImage(128, 32, "衡水中学", 32));
		textSquare9 = new TextSquare();
		textSquare9.loadBitmap(GLFont.getImage(128, 32, "上海中学", 32));
		textSquare10 = new TextSquare();
		textSquare10.loadBitmap(GLFont.getImage(128, 32, "华科附中", 32));
		textSquare11 = new TextSquare();
		textSquare11.loadBitmap(GLFont.getImage(128, 32, "巴蜀中学", 32));
		textSquare12 = new TextSquare();
		textSquare12.loadBitmap(GLFont.getImage(128, 32, "大同一中", 32));
		textSquare13 = new TextSquare();
		textSquare13.loadBitmap(GLFont.getImage(128, 32, "海南中学", 32));
	}

	public void setShowFont(boolean isShowFont) {
		this.isShowFont = isShowFont;
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Sets the current view port to the new size.
		gl.glViewport(0, 0, width, height);
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// Reset the modelview matrix
		gl.glLoadIdentity();
	}

	@Override
	public void onDrawFrame(GL10 gl) {

		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// Replace the current matrix with the identity matrix
		gl.glLoadIdentity();
		// Translates 10 units into the screen.
		gl.glTranslatef(-0.5f, 1.4f / (float) Math.sqrt(scale2) - 0.5f + ((float) Math.sqrt(scale2) - 1f), -8.5f + (float) (Math.sqrt(scale / 10))
				+ (1.5f - scale2));
		gl.glRotatef(30f, 0f, 0f, 1f);

		// 绘制多面体
		// Save the current matrix.
		gl.glPushMatrix();
		// Rotate square A counter-clockwise.
		gl.glRotatef(angle / 2, 0, 1, 0);

		hexahedron.setHexachedronAlpha(hexachedronAlpha, cubeColors);
		hexahedron.setFlag(false);
		hexahedron.draw(gl);
		sixCone.setSixConeAlpha(sixConeAlpha, cubeColors);
		sixCone.setFlag(false);
		sixCone.draw(gl);
		antiSixCone.setAntiSixConeAlpha(antiSixConeAlpha, cubeColors);
		antiSixCone.setFlag(false);
		antiSixCone.draw(gl);
		// Restore the last matrix.
		gl.glPopMatrix();

		// 绘制字体的bitmap，添加顺序绑定了多面立方体的坐标，不能随意改动
		drawFont(gl, textSquare1, -1.5f, 0, 0, false);

		drawFont(gl, textSquare5, -1, 0, 1.5f, false);

		drawFont(gl, textSquare4, 1, 0, 1.5f, false);

		drawFont(gl, textSquare, 1.5f, 0, 0, false);

		drawFont(gl, textSquare2, 1, 0, -1.5f, false);

		drawFont(gl, textSquare3, -1, 0, -1.5f, false);

		drawFont(gl, textSquare8, (float) (-Math.sqrt(3)) / 4 * 3, -2, -1, false);

		drawFont(gl, textSquare9, (float) (-Math.sqrt(3)) / 4 * 3, -2, 1, false);

		drawFont(gl, textSquare10, 0, -2, 1.5f, false);

		drawFont(gl, textSquare11, (float) (Math.sqrt(3)) / 4 * 3, -2, 1, false);

		drawFont(gl, textSquare6, (float) (Math.sqrt(3)) / 4 * 3, -2, -1, false);

		drawFont(gl, textSquare7, 0, -2, -1.5f, false);

		drawFont(gl, textSquare12, 0, 1.2f, 0, false);

		drawFont(gl, textSquare13, 0, -3.2f, 0, false);

		// 隐藏文字以后，学校信息依旧不隐藏

		// drawFont(gl, textSquareTitle, 0, -1, 0, true);
		//
		// drawFont(gl, textSquareTitle2, -0.25f, -1.4f, 0, true);

		// Increse the angle.
		if (angle + 1 == 720) {
			angle = 0;
		} else {
			angle++;
		}
	}

	private void drawFont(GL10 gl, TextSquare textSquare, float x, float y, float z, boolean isShouldShow) {
		// Save the current matrix
		gl.glPushMatrix();
		// Rotate square B before moving it,
		// making it rotate around A.
		if (isShouldShow != true) {
			gl.glRotatef(angle / 2, 0, 1, 0);
			gl.glRotatef(-2f, 0, 0, 1);
		}
		// Move square B.
		gl.glTranslatef(x, y, z);
		// Scale it to 50% of square A
		// gl.glScalef(.5f, .5f, .5f);
		if (isShouldShow != true) {
			gl.glRotatef(-angle / 2, 0, 1, 0);
		}
		if (isShouldShow) {
			gl.glRotatef(-30f, 0, 0, 1);
		}
		gl.glRotatef(-30f, 0, 0, 1);

		// 值域映射，有0到1映射成0.66到1
		float alpha = calcAngle(x, y, z, angle / 2) / 3 + 0.66f;

		// 因为文字贴图有14个，第13个和14个为上下顶点，不需要改变alpha值
		if (i + 1 > 16) {
			i = 0;
		}
		if (i < 12) {
			if (themeColor == -10065257) {
				// 夜空主题用这个
				hexachedronAlpha[i] = (alpha - 0.62f) * 3;
			} else {
				hexachedronAlpha[i] = alpha / 2 + 0.5f;
			}
		}
		if (i < 6) {
			if (themeColor == -10065257) {
				// 夜空主题用这个
				sixConeAlpha[i] = (alpha - 0.66f) * 3;
			} else {
				sixConeAlpha[i] = alpha / 2 + 0.5f;
			}
		}
		if (i >= 7 && i <= 12) {
			if (themeColor == -10065257) {
				// 夜空主题用这个
				antiSixConeAlpha[i - 7] = (alpha - 0.66f) * 3;
			} else {
				antiSixConeAlpha[i - 7] = alpha / 2 + 0.5f;
			}
		}
		i++;

		//textSquare.setFontAlpha(alpha);
		
		// Draw square B.
		textSquare.setFlag(true);
		if (isShowFont || isShouldShow) {
			textSquare.draw(gl);
		}
		// Restore to the matrix as it was before B.
		gl.glPopMatrix();
	}

	// 渐变逻辑，返回的是alpha值
	public static float calcAngle(float x, float y, float z, int angle) {
		float angleOffset = (float) (angle * Math.PI / 180);
		if (y == 0 && z == 0) {
			if (x == 1.5f) {
				return (float) (0.5 * Math.sin(angleOffset - Math.PI) + 0.5);
			} else if (x == -1.5f) {
				return (float) (0.5 * Math.sin(angleOffset) + 0.5);
			}
		} else if (y == 0 && z == -1.5f) {
			if (x == 1) {
				return (float) (0.5 * Math.sin(angleOffset - 2 * Math.PI / 3) + 0.5);
			} else if (x == -1) {
				return (float) (0.5 * Math.sin(angleOffset - Math.PI / 3) + 0.5);
			}
		} else if (y == 0 && z == 1.5f) {
			if (x == 1) {
				return (float) (0.5 * Math.sin(angleOffset + 2 * Math.PI / 3) + 0.5);
			} else if (x == -1) {
				return (float) (0.5 * Math.sin(angleOffset + Math.PI / 3) + 0.5);
			}
		} else if (y == -2f && z == -1) {
			if (x == (float) (Math.sqrt(3)) / 4 * 3) {
				return (float) (0.5 * Math.sin(angleOffset - 5 * Math.PI / 6) + 0.5);
			} else if (x == (float) (-Math.sqrt(3)) / 4 * 3) {
				return (float) (0.5 * Math.sin(angleOffset - Math.PI / 6) + 0.5);
			}
		} else if (y == -2f && z == 1) {
			if (x == (float) (Math.sqrt(3)) / 4 * 3) {
				return (float) (0.5 * Math.sin(angleOffset + 5 * Math.PI / 6) + 0.5);
			} else if (x == (float) (-Math.sqrt(3)) / 4 * 3) {
				return (float) (0.5 * Math.sin(angleOffset + Math.PI / 6) + 0.5);
			}
		} else if (x == 0 && y == -2) {
			if (z == -1.5f) {
				return (float) (0.5 * Math.sin(angleOffset - Math.PI / 2) + 0.5);
			} else if (z == 1.5f) {
				return (float) (0.5 * Math.sin(angleOffset - 3 * Math.PI / 2) + 0.5);
			}
		}
		return 1;
	}
}
