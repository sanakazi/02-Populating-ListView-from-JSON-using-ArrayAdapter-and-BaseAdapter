package com.example.poplistview;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SecondActivity extends Activity {
	private ListView listView;
    String email, gender, address, name;
    private CustomAdapter blipAdapter;
    @Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		getHomeData();
	
	}


	private void getHomeData() {
		// TODO Auto-generated method stub
		 ArrayList<Model> arrayList=new ArrayList<Model>();
		listView=(ListView) findViewById(R.id.list1);
		String input_data= "http://api.androidhive.info/contacts/"; //URL website anda dengan file insert.php yang telah dibuat  
        
        HttpClient httpClient = new DefaultHttpClient();  
        HttpPost httpPost = new HttpPost(input_data);  
        Log.d("HTTP POST", httpPost.toString());
      try
      {
     //   ArrayList<NameValuePair> param = new ArrayList<NameValuePair>(); 
     //   httpPost.setEntity(new UrlEncodedFormEntity(param));  
       	HttpResponse httpRespose = httpClient.execute(httpPost);  
       	HttpEntity httpEntity = httpRespose.getEntity();  
        Log.d("HTTP Entity", httpEntity.toString());
      	InputStream in = httpEntity.getContent();  
      	BufferedReader read = new BufferedReader(new InputStreamReader(in));  
      	String isi="";
  		String baris="";
  		
  	   while((baris = read.readLine())!=null)
  	   		{  
  		   		isi+= baris;  
  	   		}
  	 if(!isi.equals("null")){                    
         	try
        		{
         		//Toast.makeText(this, isi, Toast.LENGTH_SHORT).show();
        			
        			JSONArray jArray = new JSONArray(isi);
      	        	 Log.d("array", jArray.toString());
           	        JSONObject json_data=null;
           	        
           	        	for(int i=0;i<jArray.length();i++)
           	        		{
           	        		Model object =new Model();
         	                
           	        		json_data = jArray.getJSONObject(i);
           	        	 Log.d("data", json_data.toString());
           	        	String id=json_data.getString("id").toString();
           	        	Log.d("id", id);
         	                name=json_data.getString("name").toString();
         	            //    image="http://10.0.2.2/homeopathic-7/my/"+json_data.getString("above").toString();
         	               email=json_data.getString("email").toString();
         	              address=json_data.getString("address").toString();
         	             gender=json_data.getString("gender").toString();
         	                object.setName(name);
         	                //object.setImage(image);
         	              arrayList.add(object);
           	        }
           	    //     blipAdapter=new CustomAdapter(this,arrayList);
         	        listView.setAdapter(blipAdapter);
         	    
           	        	}
        		catch (Exception e) {
					// TODO: handle exception
				}
  	 
  	 
  	 }
  	 else
  	 {
  		 
  	 }
      }
      catch (Exception e) {
		// TODO: handle exception

	}
	}
	

	
	
}

