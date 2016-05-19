package com.example.poplistview;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	String name, email, address, gender;
	int id;
	  ListView list;
	   CustomAdapter adapter;
	   String resp,strJson;
	   Resources res;
	   ArrayList<Model> arr;
	
	  
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
       //setListData();
		arr = new ArrayList<Model>();
		  list= ( ListView )findViewById( R.id.list );
		  res =getResources();
        AsyncWS task = new AsyncWS();
        task.execute();
        
        
	}
	
	  /****** Function to set data in ArrayList *************/
    public void setListData()
    {
    	
    	
    	 HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost("http://api.androidhive.info/contacts/");
		
		   try{
		 HttpResponse response = httpclient.execute(httppost);
                  // write response to log
		    Log.d("Http Post Response:", response.toString());
		 
		    String str = inputStreamToString(response.getEntity().getContent()).toString();
		     Log.d("Http Post to string:", str.toString());
		     strJson=str.toString();  
		     
		     JSONObject  jsonRootObject = new JSONObject(strJson);
		   
		      
			   //Get the instance of JSONArray that contains JSONObjects
		       JSONArray jsonArray = jsonRootObject.optJSONArray("contacts");	
		        Log.d("Json array", jsonArray.toString());
		        
		        
		        //Iterate the jsonArray and print the info of JSONObjects
		         for(int i=0; i < jsonArray.length(); i++)
		         {
		            JSONObject jsonObject = jsonArray.getJSONObject(i);
		            Log.d("Json object:", jsonObject.toString());
		            
		         //    int id = Integer.parseInt(jsonObject.optString("id").toString());
		             String id = jsonObject.optString("id").toString();
		             Log.d("id",id.toString());
		             String name = jsonObject.optString("name").toString();
		              Log.d("name:", name);
		              String email = jsonObject.optString("email").toString();
		              String address = jsonObject.optString("address").toString();
		              String gender = jsonObject.optString("gender").toString();

		              //******* Firstly take data in model object ******/
		              Model sched = new Model();
		               sched.setName("name "+ name);
		               sched.setAddress("address"+address);
		               sched.setGender("gender"+ gender);
		               sched.setEmail("email"+ email);
		             
		                
		            /******** Take Model Object in ArrayList **********/
		          arr.add( sched);
		          
		         }
		            Log.d("Elements", arr.toString());
		            // List defined in XML ( See Below )
		            
		            /**************** Create Custom Adapter *********/
		          
		            
		          
		            
		   
		   }
		   
		   catch (ClientProtocolException e) {
			    // TODO Auto-generated catch block
			} catch (IOException e) {
			    // TODO Auto-generated catch block
			}
			 catch (JSONException e) {e.printStackTrace();}
			 
    
        
    }

    private StringBuilder inputStreamToString(InputStream is) {
	     String line = "";
	     StringBuilder total = new StringBuilder();
	     // Wrap a BufferedReader around the InputStream
	     BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	     // Read response until the end
	     try {
	      while ((line = rd.readLine()) != null) { 
	        total.append(line); 
	      }
	     } catch (IOException e) {
	      e.printStackTrace();
	     }
	     // Return full string
	     return total;
	    }
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class AsyncWS extends AsyncTask{

		ProgressDialog dialog;
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(MainActivity.this);
			//dialog.setIndeterminate(true);
		    dialog.setCancelable(false);
			dialog.setMessage("Please wait...");

			dialog.show();

		}
	@Override
	protected Object doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		setListData();
		
		return list;
		
	}
	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		

		dialog.dismiss();
		  adapter=new CustomAdapter( MainActivity.this, arr );
          list.setAdapter( adapter );
		
		 /* adapter=new CustomAdapter( MainActivity.this, arr,res );
          list.setAdapter( adapter );
          */
		}
		
	}

}

