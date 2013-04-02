package com.example.foodcpu;

import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class TakePictureTask {

	 private Camera camera;
	 private final Context context;
	 
	 	public TakePictureTask(Camera camera, Context context) {
		    this.context = context;
		    this.camera = camera;
		    Log.w("TakePictureTask", "TakePictureTask");
		  }
	

	   
	    public void doInBackground() {
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

	    }
	    
}
