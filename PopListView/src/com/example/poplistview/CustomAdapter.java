package com.example.poplistview;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/*
public class CustomAdapter extends BaseAdapter{
	 //*********** Declare Used Variables *********//*
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    Model tempValues=null;

    //******** What is the size of Passed Arraylist Size ************//*
    public int getCount() {
         
        if(data.size()<=0)
            return 1;
        return data.size();
    }
	//*************  CustomAdapter Constructor *****************//*
	 public CustomAdapter(Activity a,ArrayList d,Resources resLocal)
	 {

         //********** Take passed values **********//*
          activity = a;
          data=d;
          res = resLocal;
       
          //***********  Layout inflator to call external xml layout () ***********//*
           inflater = ( LayoutInflater )activity.
                                       getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 }
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	 //********* Create a holder Class to contain inflated xml file elements *********//*
    public static class ViewHolder{
         
        public TextView name, email, address, gender;
 
    }

    //****** Depends upon data size called for each row , Create each ListView row *****//*
    public View getView(int position, View convertView, ViewGroup parent) {
         
        View vi = convertView;
        ViewHolder holder;
         
        if(convertView==null){
             
            //****** Inflate tabitem.xml file for each row ( Defined below ) *******//*
            vi = inflater.inflate(R.layout.item_list, null);
             
            //****** View Holder Object to contain tabitem.xml file elements ******//*

            holder = new ViewHolder();
            holder.name = (TextView) vi.findViewById(R.id.name);
            holder.email=(TextView)vi.findViewById(R.id.email);
            holder.address=(TextView)vi.findViewById(R.id.address);
            holder.gender=(TextView)vi.findViewById(R.id.gender);
           //************  Set holder with LayoutInflater ************//*
            vi.setTag( holder );
        }
        else 
            holder=(ViewHolder)vi.getTag();
         
        if(data.size()<=0)
        {
            holder.name.setText("No Data");
             
        }
        else
        {
            //***** Get each Model object from Arraylist ********//*
            tempValues=null;
            tempValues = ( Model ) data.get( position );
             
            //************  Set Model values in Holder elements ***********//*

             holder.name.setText( tempValues.getName() );
             holder.email.setText( tempValues.getEmail() );
             holder.address.setText(tempValues.getAddress());
             holder.gender.setText(tempValues.getGender());
       
            
        }
        return vi;
	}

}*/

public class CustomAdapter extends ArrayAdapter<Model>{
	
	private final Activity context;
	private ArrayList<Model> names;
	
	public CustomAdapter(Activity context,ArrayList<Model>names ) {
		super(context, R.layout.item_list,names);
		this.context=context;
		this.names=names;
		// TODO Auto-generated constructor stub
	}
static class ViewHolder{
	 public TextView name, email, address, gender;
	
}
public View getView(int position, View convertView, ViewGroup parent) {
	final ViewHolder holder;
	View rowView=convertView;
	if(rowView==null){
		 LayoutInflater inflater = context.getLayoutInflater();
		rowView = inflater.inflate(R.layout.item_list, null, true);
		            	holder = new ViewHolder(); 
			            holder.name = (TextView) rowView.findViewById(R.id.name);
			            holder.email = (TextView) rowView.findViewById(R.id.email);
			            holder.address = (TextView) rowView.findViewById(R.id.address);
			            holder.gender = (TextView) rowView.findViewById(R.id.gender);
			            rowView.setTag(holder);
	}else{
		holder=(ViewHolder)rowView.getTag();
	}
	Model blipVar=names.get(position);
	if(blipVar!=null)
	{
		holder.name.setText(blipVar.getName());
		holder.email.setText(blipVar.getEmail());
		holder.address.setText(blipVar.getAddress());
		holder.gender.setText(blipVar.getGender());
		//holder.imageView.setImageDrawable(Conf.loadImageFromURL(blipVar.getImage()));
			 
			        
	}
	
	return rowView;
	
}
}
