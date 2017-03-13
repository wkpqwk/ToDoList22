package com.example.todolist;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;


import com.tubb.smrv.SwipeHorizontalMenuLayout;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ArrayList<ListData> Grouplist =new ArrayList<>();
    ArrayList<ArrayList<String>> Childlist=new ArrayList<>();
    ArrayList<String> ChildlistContent = new ArrayList();
    BaseExpandableListAdapter base;
    String s;
    private ExpandableListView expandableListView;
    SwipeHorizontalMenuLayout sml ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       expandableListView =(ExpandableListView)findViewById(R.id.expandablelist);
       ListDataBase listDataBase = new ListDataBase(getApplicationContext(),"list.db",null,3);
       String k="zxc";
     //listDataBase.insert("insert into Todo_ List values(null,'person','"+ k + "','no','10');");
       ArrayList<String> arr=listDataBase.search();
       Grouplist.add(new ListData("오늘",String.valueOf(arr.size())));
       Grouplist.add(new ListData("내일",String.valueOf(arr.size())));
       Grouplist.add(new ListData("이번주",String.valueOf(arr.size())));
       Grouplist.add(new ListData("나중에","0"));
       String z=String.valueOf(arr.size());                             //db를 검색하기 위해
       for(int i=0;i<arr.size();i++){
           ChildlistContent.add(arr.get(i));
       }

       Childlist.add(ChildlistContent);
       Childlist.add(ChildlistContent);
       Childlist.add(ChildlistContent);
       Childlist.add(ChildlistContent);

       base= new BaseExpandableAdapter(this,Grouplist,Childlist);

        expandableListView.setAdapter(base);
       expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
           @Override
           public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
               Toast.makeText(getApplicationContext()," 그룹 터치",Toast.LENGTH_SHORT).show();
               return false;
           }
       });
    }
    public void refresh(){
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }
    @Override
    public void onBackPressed() {
        FragmentManager fm= getFragmentManager();
        Fragment fragment= fm.findFragmentByTag("search");
        if(fragment != null) {
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
