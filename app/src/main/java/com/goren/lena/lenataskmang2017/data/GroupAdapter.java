package com.goren.lena.lenataskmang2017.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.goren.lena.lenataskmang2017.R;

/**
 * Created by user on 20/08/2017.
 */

public class GroupAdapter extends ArrayAdapter<MyGroup>
{

    public GroupAdapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
            convertView= LayoutInflater.from((getContext())).inflate(R.layout.itm_group,parent,false);

        TextView tvGroupName= (TextView) convertView.findViewById(R.id.itmTVGroupName);
        TextView tvOwnerName= (TextView) convertView.findViewById(R.id.itmTVGroupOwner);
        MyGroup myGroup=getItem(position);
        tvGroupName.setText(myGroup.getName());
        //todo לקבל שם אמיתי של הקבוצה
        tvOwnerName.setText(myGroup.getMngrUKey());

        return convertView;
    }
}
