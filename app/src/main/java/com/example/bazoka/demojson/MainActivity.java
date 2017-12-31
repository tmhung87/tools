package com.example.bazoka.demojson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ToolsAdapter toolsAdapter;
    ArrayList<Tools> toolsArrayList;
    String url = "http://192.168.1.19:81/tools/getdata.php";
    String urlinsert = "http://192.168.1.19:81/tools/setdata.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        toolsArrayList = new ArrayList<>();
        toolsAdapter = new ToolsAdapter(this, R.layout.tools, toolsArrayList);
        listView.setAdapter(toolsAdapter);



        getData(url);
        setData(urlinsert);
    }

    private void anhxa() {
        listView = (ListView) findViewById(R.id.listviewtools);
    }

    private void getData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        toolsArrayList.add(new Tools(
                                jsonObject.getInt("ID"),
                                jsonObject.getString("TEN"),
                                jsonObject.getString("PN"),
                                jsonObject.getString("SN"),
                                jsonObject.getString("ANH1"),
                                jsonObject.getString("ANH2"),
                                jsonObject.getString("ANH3")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                toolsAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void setData(String url) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuthem:
                startActivity(new Intent(MainActivity.this,UploadNewTool.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
