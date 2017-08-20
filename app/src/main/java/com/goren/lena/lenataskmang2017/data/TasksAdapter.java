package com.goren.lena.lenataskmang2017.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.goren.lena.lenataskmang2017.R;

import java.util.Date;

/**
 * Created by user on 17/08/2017.
 */

public class TasksAdapter extends ArrayAdapter<MyTask>
{
    public TasksAdapter(Context context, int resource)
    {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.itm_task,parent,false);
        }
        MyTask task=getItem(position);

        TextView tvText=(TextView)convertView.findViewById(R.id.itmTVText);
        CheckBox cbIsCoplited=(CheckBox)convertView.findViewById(R.id.itmCBIsCompleted);
        TextView tvCreatedAt=(TextView)convertView.findViewById(R.id.itmTVCreatedAT);
        TextView tvAddress=(TextView)convertView.findViewById(R.id.itmTVAddress);
        TextView tvUser=(TextView)convertView.findViewById(R.id.itmTVUser);
        Button btnGroup=(Button)convertView.findViewById(R.id.itmbtnGroup);

        tvText.setText(task.getText());
        cbIsCoplited.setChecked(task.isCompleted());
        tvAddress.setText(task.getAddress());
        tvUser.setText(task.getuKey());
        btnGroup.setText(task.getGKey());
        Date date=new Date(task.getCreatedAt());
        tvCreatedAt.setText(date.toString());
        return convertView;
    }
}
