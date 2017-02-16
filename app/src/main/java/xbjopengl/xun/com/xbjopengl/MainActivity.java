package xbjopengl.xun.com.xbjopengl;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import xbjopengl.xun.com.xbjopengl.opengl.OpenGLRenderer;

public class MainActivity extends AppCompatActivity {
    private MyGLSurfaceView mGlSurfaceView;
    private OpenGLRenderer openGLRenderer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGlSurfaceView = (MyGLSurfaceView) findViewById(R.id.exercise_glsurfaceview_start);
        mGlSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        mGlSurfaceView.setRenderColor(Color.parseColor("#33bbff"));

        openGLRenderer = new OpenGLRenderer(getApplicationContext(), mGlSurfaceView.getRenderColor());
        openGLRenderer.setShowFont(true);
        mGlSurfaceView.setRenderer(openGLRenderer);
        mGlSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        mGlSurfaceView.setZOrderOnTop(true);
    }
}
