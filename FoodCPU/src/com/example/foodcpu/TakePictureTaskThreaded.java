package com.example.foodcpu;

import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class TakePictureTaskThreaded extends AsyncTask<Void,Void,Void> {

	 private Camera camera;
	 private final Context context;
	 
	 	public TakePictureTaskThreaded(Camera camera, Context context) {
		    this.context = context;
		    this.camera = camera;
		    Log.w("TakePictureTask", "TakePictureTask");
		  }
	  @Override
	    protected void onPostExecute(Void result) {
	        // This returns the preview back to the live camera feed
		  Log.w("TakePictureTask", "onPostExecute");
		  camera.startPreview();
		  Log.w("TakePictureTask", "onPostExecute succesful");
	    }

	    @Override
	    protected Void doInBackground(Void... params) {
	    	camera.takePicture(null, null, new PhotoHandler(context));
	    	Log.w("TakePictureTask", "doInBackground");
	        
	        // Sleep for however long, you could store this in a variable and
	        // have it updated by a menu item which the user selects.
	        try {
	            Thread.sleep(3000); // 3 second preview
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	        Log.w("TakePictureTask", "doInBackground successful!");
	        return null;
	    }
	    
}
