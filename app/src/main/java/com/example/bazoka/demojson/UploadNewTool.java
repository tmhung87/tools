package com.example.bazoka.demojson;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadNewTool extends AppCompatActivity {
    ImageView imageView1, imageView2, imageView3;
    EditText edtname, edtpn, edtsn;
    Button btnthem, btnhuy;
    String urlup = "http://192.168.1.19:81/tools/setdata.php";
    Bitmap bitmap1, bitmap2, bitmap3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_new_tool);

        anhxa();

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 999);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 998);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 997);
            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
                startActivity(new Intent(UploadNewTool.this,MainActivity.class));
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public String getStringImage(Bitmap bm) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, ba);
        byte[] imagebyte = ba.toByteArray();
        String encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);
        return encode;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK && data != null) {
            Uri filepath = data.getData();
            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView1.setImageBitmap(bitmap1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (requestCode == 998 && resultCode == RESULT_OK && data != null) {
            Uri filepath = data.getData();
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView2.setImageBitmap(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (requestCode == 997 && resultCode == RESULT_OK && data != null) {
            Uri filepath = data.getData();
            try {
                bitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView3.setImageBitmap(bitmap3);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void UploadImage() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlup, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response.trim();

                if (s.equalsIgnoreCase("Loi")) {
                    Toast.makeText(UploadNewTool.this, "Loi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UploadNewTool.this, "Thanh COng", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UploadNewTool.this, error + "", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String ten = edtname.getText().toString();
                String pn = edtpn.getText().toString();
                String sn = edtsn.getText().toString();
                String imagea1 = getStringImage(bitmap1);
                String imagea2 = getStringImage(bitmap2);
                String imagea3 = getStringImage(bitmap3);
                Map<String, String> params = new HashMap<String, String>();

                params.put("TEN", ten);
                params.put("PRN", pn);
                params.put("SN", sn);
                params.put("ANH1", imagea1);
                params.put("ANH2", imagea2);
                params.put("ANH3", imagea3);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void anhxa() {
        imageView1 = (ImageView) findViewById(R.id.imageViewanh1);
        imageView2 = (ImageView) findViewById(R.id.imageViewanh2);
        imageView3 = (ImageView) findViewById(R.id.imageViewanh3);
        edtname = (EditText) findViewById(R.id.edtname);
        edtpn = (EditText) findViewById(R.id.edtpn);
        edtsn = (EditText) findViewById(R.id.edtsn);
        btnthem = (Button) findViewById(R.id.btnthem);
        btnhuy = (Button) findViewById(R.id.btnhuy);

    }

}
