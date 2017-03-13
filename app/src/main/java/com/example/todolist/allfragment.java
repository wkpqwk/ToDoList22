package com.example.todolist;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.markushi.ui.CircleButton;

/**
 * Created by 재윤 on 2017-03-08.
 */

public class allfragment extends Fragment {
    EditText editText1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View c = inflater.inflate(R.layout.listadd_fragment,container,false);
        final ListDataBase listDataBase = new ListDataBase(getActivity().getApplicationContext(),"list.db",null,3);

        CircleButton Circlebtn = (CircleButton)c.findViewById(R.id.planplus);
        editText1 = (EditText)c.findViewById(R.id.planedit);
        Circlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =editText1.getText().toString();
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat format = new SimpleDateFormat("yyyy년 m월 d일 a hh시 mm분 ss초");
                format.format(date);
                listDataBase.insert("insert into Todo_List3 values(null,'person','"+ s + "','no','"+format+"');");
                FragmentManager fm= getFragmentManager();
                Fragment fragment= fm.findFragmentByTag("search");
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.remove(fragment);
                fragmentTransaction.commit();
                ((MainActivity)getActivity()).refresh();
            }
        });
        return c;
    }

}
