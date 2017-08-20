package com.goren.lena.lenataskmang2017.main_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.goren.lena.lenataskmang2017.R;
import com.goren.lena.lenataskmang2017.data.DBUtils;
import com.goren.lena.lenataskmang2017.data.MyTask;
import com.goren.lena.lenataskmang2017.data.TasksAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTasksFragment extends Fragment implements TitleAble {

     private ListView lstMyTasks;
    private TasksAdapter tasksAdapter;

    public MyTasksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_tasks, container, false);
        lstMyTasks=(ListView)view.findViewById(R.id.lstVTasks);
        //todo connect the listview to data source by Adapter
        initListView();

        return view;

    }

    private void initListView()
    {
        if(tasksAdapter==null)
        {
            tasksAdapter=new TasksAdapter(getActivity(),R.layout.itm_task);
        }
        tasksAdapter.clear();
        //todo get data source and set it to the adapter
        DBUtils.myTasksRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//               for (int i=0;i<dataSnapshot.getChildrenCount();i++)
//               {
//                   DataSnapshot ds=dataSnapshot.getChildren().iterator().next();
//                   MyTask myTask=ds.getValue(MyTask.class);
//                   tasksAdapter.add(myTask);
//               }
                //foreach
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                          MyTask myTask=ds.getValue(MyTask.class);
                          tasksAdapter.add(myTask);
                }
                lstMyTasks.setAdapter(tasksAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lstMyTasks.setAdapter(tasksAdapter);
    }

    public String getTitle()
    {
        return "My Tasks";
    }

}
