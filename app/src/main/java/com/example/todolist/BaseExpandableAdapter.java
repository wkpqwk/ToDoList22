package com.example.todolist;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tubb.smrv.SwipeHorizontalMenuLayout;
import com.tubb.smrv.listener.SwipeSwitchListener;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;

import static com.example.todolist.R.id.sml;

/**
 * Created by 재윤 on 2017-03-09.
 */

public class BaseExpandableAdapter extends BaseExpandableListAdapter{
    private ArrayList<ListData> grouplist = null;
    private ArrayList<ArrayList<String>> childList = null;
    final int TAKE_PICTURE = 200;
    ListDataBase db;
    private LayoutInflater inflater = null;
    private ViewHolder viewHolder=null;
    private ViewHolder2 viewHolder2=null;
    class ViewHolder{
        public TextView tv1;
        public TextView tv2;
        public CircleButton btn;
    }
    class ViewHolder2{
        public TextView tv3;
        public TextView tv4;
    }
    public BaseExpandableAdapter(Context c, ArrayList<ListData> grouplist, ArrayList<ArrayList<String>> childList) {
        super();
        this.inflater = LayoutInflater.from(c);
        this.grouplist = grouplist;
        this.childList = childList;
    }
    public void setNewItem(Context c, ArrayList<ListData> grouplist, ArrayList<ArrayList<String>> childList) {
        this.inflater = LayoutInflater.from(c);
        this.grouplist = grouplist;
        this.childList = childList;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return grouplist.size();
    }

    @Override
    public ListData getGroup(int groupPosition) {
        return grouplist.get(groupPosition);
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v =convertView ;
        final Context context = parent.getContext();
        if(v==null){
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.list_row,parent,false);
            viewHolder.tv1 =(TextView)v.findViewById(R.id.dayview);
            viewHolder.tv2=(TextView)v.findViewById(R.id.countview);
            viewHolder.btn=(CircleButton) v.findViewById(R.id.addbtn);
            v.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)v.getTag();
        }
        viewHolder.tv1.setText(getGroup(groupPosition).getDay());
        viewHolder.tv2.setText(getGroup(groupPosition).getCount());
        if(getGroup((groupPosition)).getCount().equals("0")==true){
            viewHolder.tv2.setVisibility(View.INVISIBLE);
            viewHolder.btn.setVisibility(View.VISIBLE);
        }else{
            viewHolder.tv2.setVisibility(View.VISIBLE);
            viewHolder.btn.setVisibility(View.INVISIBLE);
        }
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new allfragment();
                FragmentManager fragmentManager = ((Activity)context).getFragmentManager();
                Bundle bundle =new Bundle();
                fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(fragment,"search");
                fragmentTransaction.replace(R.id.activity_main,fragment);
                fragmentTransaction.commit();
            }
        });

        return v;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v==null){
            viewHolder2  = new ViewHolder2();
            v = inflater.inflate(R.layout.clist_low,null);
            viewHolder2.tv3 =(TextView)v.findViewById(R.id.cdayview);
            viewHolder2.tv4 =(TextView)v.findViewById(R.id.check);
            v.setTag(viewHolder2);
        }else{
            viewHolder2 =(ViewHolder2)v.getTag();
        }

        v.findViewById(R.id.btDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db= new ListDataBase(v.getContext().getApplicationContext(),"list.db",null,3);
                db.delete("DELETE FROM Todo_List3 WHERE title == '"+String.valueOf(getChild(groupPosition,childPosition))+"';");
                Toast.makeText(v.getContext().getApplicationContext(),String.valueOf(getChild(groupPosition,childPosition)),Toast.LENGTH_SHORT).show();
                viewHolder2.tv3 =(TextView)v.findViewById(R.id.cdayview);
            }
        });
        viewHolder2.tv3.setText(getChild(groupPosition,childPosition));

        return v;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }

}
