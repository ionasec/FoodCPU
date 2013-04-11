package com.example.foodcpu;

import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;


@TargetApi(9)
public class MainActivity extends Activity implements SurfaceHolder.Callback {

	private final static String DEBUG_TAG = "MakePhotoActivity";
	private Camera camera;
	private int cameraId = 0;
	private SurfaceView preview;
	private SurfaceHolder holder;	 
	TakePictureTask takePictureTask;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
              
        preview = (SurfaceView) findViewById(R.id.surfaceView1);
        holder = preview.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
       
        
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG)
        .show();
        
        // do we have a camera?
        if (!getPackageManager()
            .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
          Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
              .show();
        }
      }
    
    public void onClick(View view) {
    
    	Toast.makeText(this, "onClick(View view)", Toast.LENGTH_LONG).show();
    	TakePictureTaskThreaded takePictureTaskThreaded= new TakePictureTaskThreaded(camera,getApplicationContext());
        takePictureTaskThreaded.execute();
    
     }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    	cameraId = findBackFacingCamera();
        if (cameraId < 0) {
          Toast.makeText(this, "No front facing camera found.",
              Toast.LENGTH_LONG).show();
        }else
        {
	        camera = Camera.open(cameraId);
	        if(camera!=null)
	        {
		        try {
		            camera.setPreviewDisplay(holder);
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
	        }
        }
    }

    
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    	if(camera!=null)
        {
	    	Camera.Parameters p = camera.getParameters();
	        // Camera.Size s = p.getSupportedPreviewSizes().get(0);
	        p.setPreviewSize(width, height);
	
	        camera.setParameters(p);
	
	        try {
	        	camera.setPreviewDisplay(holder);
	        } catch (Exception e) {
	        }
	
	        camera.startPreview();
        }
    }
    
    private int findBackFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
          CameraInfo info = new CameraInfo();
          Camera.getCameraInfo(i, info);
          if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
            Log.d(DEBUG_TAG, "Camera found");
            cameraId = i;
            break;
          }
        }
        return cameraId;
      }
    
    @Override
    protected void onPause() {
      if (camera != null) {
        camera.release();
        camera = null;
      }
      super.onPause();
    }
    
  
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (camera!=null)
        {
            camera.stopPreview();
            camera.release();
            camera=null;
        }

    }
}



