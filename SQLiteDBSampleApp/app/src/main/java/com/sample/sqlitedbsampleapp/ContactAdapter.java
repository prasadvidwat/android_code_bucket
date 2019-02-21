package com.sample.sqlitedbsampleapp;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.zip.Inflater;

public class ContactAdapter extends ArrayAdapter<Contact> //implements View.OnClickListener {
{

    Context appContext;
    int resource;
    ItemChangeRequestListener listener;

    public ContactAdapter(@NonNull Context context, int resource) {
        super(context, resource);

        this.appContext = context;
        this.resource = resource;
        this.listener = (MainActivity) context;
    }

//    @Override
//    public void onClick(View view) {
//
//        if(view.getId() == R.id.btn_update_contact)
//            listener.onDataUpdate();
//        else
//            listener.onDataDelete();
//    }

    public class ViewHolder
    {
        TextView txtId, txtName, txtNumber;
        Button btnUpdate, btnDelete;

        public ViewHolder(View convertView) {
            txtId = (TextView) convertView.findViewById(R.id.txt_show_contact_id);
            txtName = (TextView) convertView.findViewById(R.id.txt_show_contact_name);
            txtNumber = (TextView) convertView.findViewById(R.id.txt_show_contact_number);
            btnUpdate = (Button) convertView.findViewById(R.id.btn_update_contact);
            btnDelete = (Button) convertView.findViewById(R.id.btn_delete_contact);
        }

        void setDataToCell(int position)
        {
            Contact contact = getItem(position);

            txtId.setText(""+contact.getId());
            txtName.setText(contact.getName());
            txtNumber.setText(contact.getNumber());
        }
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDataDelete(getItem(position));
            }
        });

        viewHolder.setDataToCell(position);

        return convertView;
    }



    @Nullable
    @Override
    public Contact getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
