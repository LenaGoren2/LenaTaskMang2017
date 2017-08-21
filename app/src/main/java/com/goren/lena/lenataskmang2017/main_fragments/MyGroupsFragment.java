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
import com.goren.lena.lenataskmang2017.data.GroupAdapter;
import com.goren.lena.lenataskmang2017.data.MyGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGroupsFragment extends Fragment implements TitleAble {

    private ListView lstVGroups;
    private GroupAdapter groupAdapter;

    public MyGroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_groups, container, false);
        lstVGroups=(ListView)view.findViewById(R.id.lstVGroups);
        groupAdapter=new GroupAdapter(getActivity(),R.layout.itm_group);
        initListView();

        return view;

    }
    // connect the list view to the data source by Adapter
    private void initListView()
    {
        DBUtils.myGroupRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                groupAdapter.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    MyGroup myGroup=ds.getValue(MyGroup.class);
                    groupAdapter.add(myGroup);
                }
                lstVGroups.setAdapter(groupAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public String getTitle()
    {
        return "My Groups";
    }

}
