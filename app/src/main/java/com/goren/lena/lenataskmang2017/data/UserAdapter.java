package com.goren.lena.lenataskmang2017.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.goren.lena.lenataskmang2017.R;

import java.util.ArrayList;

/**
 * Created by user on 21/08/2017.
 */

public class UserAdapter extends ArrayAdapter<MyUser>
{
    private int target;
    public final static int GROUPSMEMBERS=1;
    public final static int SEARCH_RESULT_MEMBERS=2;
    public final static int SELECTED_MEMBERS=3;
    public final static int SPINER_MEMBERS=4;
    private ArrayList<MyUser> selectedUsers;

    public UserAdapter(Context context, int resource, int target)
    {
        super(context, resource);
        this.target=target;
        this.selectedUsers=new ArrayList<MyUser>();

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from((getContext())).inflate(R.layout.itm_user,parent,false);

        TextView tvUserEmail= (TextView) convertView.findViewById(R.id.itmtvUserEmail);
        TextView tvUserName= (TextView) convertView.findViewById(R.id.itmtvUserName);
        ImageButton ibtnUserCall=(ImageButton)convertView.findViewById(R.id.itmbtnUserCall);
        ImageButton ibtnUserDel=(ImageButton)convertView.findViewById(R.id.itmbtnUserDel);
        CheckBox cbSelect=(CheckBox)convertView.findViewById(R.id.itmCBSelect);

        final MyUser myUser=getItem(position);

        tvUserEmail.setText(myUser.getuKey_email());
        tvUserName.setText(myUser.getName());

        switch (target)
        {
            case GROUPSMEMBERS:
                ibtnUserDel.setVisibility(View.GONE);
                cbSelect.setVisibility(View.GONE);
                ibtnUserCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        call(myUser.getPhone());
                    }
                });
                break;

            case SEARCH_RESULT_MEMBERS:
                ibtnUserCall.setVisibility(View.GONE);
                ibtnUserDel.setVisibility(View.GONE);
                cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        select(myUser,isChecked);
                    }
                });
                break;

            case SELECTED_MEMBERS:
                ibtnUserCall.setVisibility(View.GONE);
                cbSelect.setVisibility(View.GONE);
                ibtnUserDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteMember(myUser);
                    }
                });
                break;

            case SPINER_MEMBERS:
                ibtnUserCall.setVisibility(View.GONE);
                cbSelect.setVisibility(View.GONE);
                ibtnUserDel.setVisibility(View.GONE);
                break;
        }


        return convertView;
    }

    public ArrayList<MyUser> getSelectedUsers()
    {
        return this.selectedUsers;
    }

    //todo delete User from the selected members
    private void deleteMember(MyUser myUser)
    {
          remove(myUser);
    }

    //todo select user
    private void select(MyUser myUser, boolean isChecked)
    {
          if(isChecked)
          {
              selectedUsers.add(myUser);
          }
        else
          {
              selectedUsers.remove(myUser);
          }
    }

    //todo make call by intent
    private void call(String phone)
    {

    }

    public void clearSelectedUsers()
    {
        selectedUsers.clear();
    }
}
