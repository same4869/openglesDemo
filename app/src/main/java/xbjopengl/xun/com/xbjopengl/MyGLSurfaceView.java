package xbjopengl.xun.com.xbjopengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * @author xunwang
 * 
 *         2015-6-2
 */
public class MyGLSurfaceView extends GLSurfaceView {
	private int renderColor;

	public int getRenderColor() {
		return renderColor;
	}

	public void setRenderColor(int renderColor) {
		this.renderColor = renderColor;
	}

	public MyGLSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGLSurfaceView(Context context) {
		super(context);
	}

}
