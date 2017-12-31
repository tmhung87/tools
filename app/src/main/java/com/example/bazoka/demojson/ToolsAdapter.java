package com.example.bazoka.demojson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by bazoka on 30/12/2017.
 */

public class ToolsAdapter extends ArrayAdapter<Tools> {
    Context context;
    public ToolsAdapter(@NonNull Context context, int resource, @NonNull List<Tools> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        Tools tools = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.tools,null);
            viewHolder = new ViewHolder();
            viewHolder.txtname = (TextView)convertView.findViewById(R.id.textViewName);
            viewHolder.txtprn = (TextView)convertView.findViewById(R.id.textViewPRN);
            viewHolder.txtsn = (TextView)convertView.findViewById(R.id.textViewSN);
            viewHolder.anh1 = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder =(ViewHolder) convertView.getTag();
        }

        viewHolder.txtname.setText(tools.ten);
        viewHolder.txtprn.setText(tools.prn);
        viewHolder.txtsn.setText(tools.sn);
        Picasso.with(context).load(tools.anh1).into(viewHolder.anh1);
        return convertView;
    }

    class ViewHolder{
        TextView txtname,txtprn,txtsn;
        ImageView anh1,anh2,anh3;

        public ViewHolder() {
        }
    }
}
