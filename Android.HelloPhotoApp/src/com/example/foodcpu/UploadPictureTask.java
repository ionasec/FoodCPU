package com.example.foodcpu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;


import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;


import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;

public class UploadPictureTask extends AsyncTask<Void,Void,Void> {
	
		String pathToOurFile;
		
		public UploadPictureTask(String pathToOurFile) {
		    this.pathToOurFile = pathToOurFile;
		 }
		
		@Override
	    protected void onPostExecute(Void result) {
	        // This returns the preview back to the live camera feed
	    }
		 
		@Override
    	protected Void doInBackground(Void... params)  {
    	
    	try
    	{
        	String line;
        	HttpResponse response;
        	BufferedReader rd;
        	String u1rlServer = "http://ionasec.com/file_upload.php";
        	
    		//HttpHost proxy = new HttpHost("proxyfarm-us.3dns.netz.sbs.de",84);
			DefaultHttpClient httpclient= new DefaultHttpClient();
			//httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
			
		/*	HttpGet httpget = new HttpGet(u1rlServer);
			HttpResponse response = httpclient.execute(httpget);
			
			// Get the response
			BufferedReader rd = new BufferedReader
			  (new InputStreamReader(response.getEntity().getContent()));
			
			line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}*/
			
			File file = new  File(pathToOurFile);
			long n = file.length();
			System.out.println("file to send:" + pathToOurFile + " file length:" +n);
			
			Log.w("TakePictureTask", "file to send:" + pathToOurFile + " file length:" +n);
			
			HttpPost httppost = new HttpPost(u1rlServer);
			
		  	MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("filename", new StringBody(pathToOurFile));
			reqEntity.addPart(pathToOurFile, new FileBody(file));
			httppost.setEntity(reqEntity);
			
			response = httpclient.execute(httppost);
			
			
			// Get the response
			rd = new BufferedReader
			  (new InputStreamReader(response.getEntity().getContent()));
			
			
			while ((line = rd.readLine()) != null) {
				Log.d("UploadPictureTask",line);
				System.out.println(line);
			} 
			
			Log.w("UploadPictureTask", "uploaded completed");
    	}
    	catch (Exception ex)
    	{
    		 ex.printStackTrace();
    		 Log.w("UploadPictureTask","failed connection :" + ex.toString());
    	}
		return null;
    	
           
    }
}
