package com.sample.roomdbtestapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by prasad.vidwat on 18/12/18.
 */

public class UserRecordAdapter extends ArrayAdapter<User> {

    Context appContext;
    int layoutResourceId;

    public UserRecordAdapter(@NonNull Context context, int resource, @NonNull List<User> list) {
        super(context, resource, list);

        appContext = context;
        layoutResourceId = resource;
    }

    static class ViewHolder
    {
        TextView txtId, txtName, txtContact;

        ViewHolder(View view)
        {
            txtId = view.findViewById(R.id.txt_id);
            txtName = view.findViewById(R.id.txt_name);
            txtContact = view.findViewById(R.id.txt_contact);
        }

        public void setData(User user)
        {
            txtId.setText(""+user.getId());
            txtName.setText(user.getName());
            txtContact.setText(user.getContact());
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(appContext).inflate(layoutResourceId, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        User user = getItem(position);
        if(user != null)
            viewHolder.setData(user);

        return convertView;
    }
}
