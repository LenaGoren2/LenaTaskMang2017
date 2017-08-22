package com.goren.lena.lenataskmang2017;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.goren.lena.lenataskmang2017.data.DBUtils;
import com.goren.lena.lenataskmang2017.data.MyGroup;
import com.goren.lena.lenataskmang2017.data.MyUser;
import com.goren.lena.lenataskmang2017.data.UserAdapter;

import java.util.ArrayList;

public class AddGroupActivity extends AppCompatActivity implements View.OnClickListener
{
     private EditText etGroupName, etEmail, etUsername;
    private ImageButton ibtnSearch, ibtnAddMember, ibtnSaveGroup;
    private ListView lstMembersSearchResult, lstvSelectedMembers;

    private UserAdapter userAdapterSearchResult, userAdapterSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        etGroupName=(EditText)findViewById(R.id.etGroupName);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etUsername=(EditText)findViewById(R.id.etName);

        ibtnSaveGroup=(ImageButton)findViewById(R.id.ibtnSaveGroup);
        ibtnSearch=(ImageButton)findViewById(R.id.ibtnSearch);
        ibtnAddMember=(ImageButton)findViewById(R.id.ibtnAddMember);

        lstMembersSearchResult=(ListView) findViewById(R.id.lstvmemberSearchResult);
        lstvSelectedMembers=(ListView) findViewById(R.id.lstvSelectedMembers);

        userAdapterSearchResult=new UserAdapter(this,R.layout.itm_user,UserAdapter.SEARCH_RESULT_MEMBERS);
        userAdapterSelected=new UserAdapter(this,R.layout.itm_user,UserAdapter.SELECTED_MEMBERS);

        lstMembersSearchResult.setAdapter(userAdapterSearchResult);
        lstvSelectedMembers.setAdapter(userAdapterSelected);

        eventHandler();

    }

    private void eventHandler()
    {
        ibtnSaveGroup.setOnClickListener(this);
        ibtnAddMember.setOnClickListener(this);
        ibtnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v==ibtnAddMember)
        {
               addSelectedUsers();
        }
        if(v==ibtnSearch)
        {
             search();
        }
        if(v==ibtnSaveGroup)
        {
             saveGroup();
        }
    }

    private void saveGroup()
    {
        String stGroupname= etGroupName.getText().toString();
        if(stGroupname.length()>2)
        {
            MyGroup myGroup=new MyGroup();
            myGroup.setName(stGroupname);
            String myEmail=DBUtils.auth.getCurrentUser().getEmail();
            myGroup.setMngrUKey(myEmail.replace('.','*'));
            final String gKey=DBUtils.myGroupRef.push().getKey();
            myGroup.setgKey(gKey);
            for (int i=0;i<userAdapterSelected.getCount();i++)
            {
                MyUser user=userAdapterSelected.getItem(i);
                myGroup.addUsersKeys(user.getuKey_email().replace('.','*'));
            }
            ProgressDialog progressDialog=ProgressDialog.show(this,"Wait","Saiving group");
            progressDialog.show();
            //todo לעדכן כל משתמש בקבוצה חדשה
            DBUtils.myGroupRef.child(gKey).setValue(myGroup).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        updateUsers(gKey);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(AddGroupActivity.this, "Adding Group Faild: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    //todo לעדכן כל משתמש בקבוצה חדשה
    private void updateUsers(String gKey)
    {
        for (int i=0;i<userAdapterSelected.getCount();i++)
        {
            MyUser user=userAdapterSelected.getItem(i);
            DBUtils.myUsersRef.child(user.getuKey_email().replace('.','*')).child("groupsKeys").child(gKey).setValue(true);
        }
    }

    private void addSelectedUsers()
    {
        ArrayList<MyUser> selectedUsers=userAdapterSearchResult.getSelectedUsers();
        userAdapterSelected.addAll(selectedUsers);
        userAdapterSearchResult.clearSelectedUsers();
    }

    private void search()
    {
        String stEmail=etEmail.getText().toString();
        String stName=etUsername.getText().toString();
        userAdapterSearchResult.clear();
        final ProgressDialog progressDialog=ProgressDialog.show(this,"Wait","Searching");

        if(stEmail.length()>0)
        {
            progressDialog.show();
            DBUtils.myUsersRef.child(stEmail.replace('.','*')).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(progressDialog.isShowing())
                          progressDialog.dismiss();
                    if(dataSnapshot.exists())
                    {
                        MyUser myUser=dataSnapshot.getValue(MyUser.class);
                        userAdapterSearchResult.add(myUser);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if(stName.length()>0)
        {
            if(!progressDialog.isShowing())
                progressDialog.show();
            //retern all users
           // DBUtils.myUsersRef.addListenerForSingleValueEvent(new ValueEventListener()
            //retern user by name
            //DBUtils.myUsersRef.orderByChild("name").equalTo(stName).addListenerForSingleValueEvent(new ValueEventListener()
             Query q=DBUtils.myUsersRef.orderByChild("name").equalTo(stName);
             q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    if(!progressDialog.isShowing())
                             progressDialog.dismiss();
                    for (DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        MyUser myUser=ds.getValue(MyUser.class);
                        userAdapterSearchResult.add(myUser);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
