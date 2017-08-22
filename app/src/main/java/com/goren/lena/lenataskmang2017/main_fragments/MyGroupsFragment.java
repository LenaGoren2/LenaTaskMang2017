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

import java.util.HashMap;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGroupsFragment extends Fragment implements TitleAble {

    private ListView lstVGroups;
    private GroupAdapter groupAdapter;

    public MyGroupsFragment() {
        // Required empty public constructor
    }

    private void initListView()
    {
        String userEmail=DBUtils.auth.getCurrentUser().getEmail();
        DBUtils.myUsersRef.child(userEmail.replace('.','*')+"/groupKeys").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                HashMap<String,Object> groupKeys=(HashMap<String,Object>) dataSnapshot.getValue();
                groupAdapter.clear();;
                if(groupKeys!=null)
                {
                    for (String gr: groupKeys.keySet() )
                    {
                        DBUtils.myGroupRef.child(gr).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                MyGroup myGroup=dataSnapshot.getValue(MyGroup.class);
                                groupAdapter.add(myGroup);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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



    public String getTitle()
    {
        return "My Groups";
    }

}
