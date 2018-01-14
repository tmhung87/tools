package com.example.bazoka.demojson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bazoka on 30/12/2017.
 */

public class ToolsAdapter extends ArrayAdapter<Tools> {
    Context context;

    String urlupdate = "https://1luutru.000webhostapp.com/deldata.php";

    public ToolsAdapter(@NonNull Context context, int resource, @NonNull List<Tools> objects) {
        super(context, resource, objects);
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        final Tools tools = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.tools, null);
            viewHolder = new ViewHolder();
            viewHolder.txtname = (TextView) convertView.findViewById(R.id.textViewName);
            viewHolder.txtprn = (TextView) convertView.findViewById(R.id.textViewPRN);
            viewHolder.txtsn = (TextView) convertView.findViewById(R.id.textViewSN);
            viewHolder.anh1 = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.imageButtonDelete = (ImageView) convertView.findViewById(R.id.imageViewDelete);
            viewHolder.imageButtonEdit = (ImageView) convertView.findViewById(R.id.imageViewEdit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtname.setText(tools.ten);
        viewHolder.txtprn.setText(tools.prn);
        viewHolder.txtsn.setText(tools.sn);
        Picasso.with(context).load(tools.anh1).into(viewHolder.anh1);


        viewHolder.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = String.valueOf(tools.id);
                String ten = tools.ten.toString();
                String anh1 = tools.anh1.toString();
                deleteitem(id,ten,anh1);

            }
        });

        viewHolder.imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateTools.class);
                intent.putExtra("aToolsvv",tools);
                context.startActivity(intent);

            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView txtname, txtprn, txtsn;
        ImageView anh1, anh2, anh3;
        ImageView imageButtonEdit, imageButtonDelete;

        public ViewHolder() {
        }
    }

    private void deleteitem(final String id,final String ten,final String anh1) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlupdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response.trim();
                Toast.makeText(context, "Delete success", Toast.LENGTH_SHORT).show();

                context.startActivity(new Intent(getContext().getApplicationContext(),MainActivity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("ID", id);
                params.put("TEN", ten);
                params.put("ANH1", anh1);
                return params;
            }
        };
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);


    }
}
