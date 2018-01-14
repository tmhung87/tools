package com.example.bazoka.demojson;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateTools extends AppCompatActivity {
    ImageView imageView1, imageView2, imageView3;
    EditText edtname, edtpn, edtsn;
    Button btnupdate, btnhuy;
    String urlup = "https://1luutru.000webhostapp.com/update.php";
    Bitmap bitmap1, bitmap2, bitmap3;
    String anh1, anh2, anh3;

    String id;
    Tools gettools, settools;

    UpdateToolService nService;
    boolean nBound = false;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(getApplicationContext(), UpdateToolService.class);
        bindService(intent, nConnection, Context.BIND_AUTO_CREATE);


    }

    private ServiceConnection nConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            UpdateToolService.UpdateBinder binder = (UpdateToolService.UpdateBinder) service;
            nService = binder.getService();
            nBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            nBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tools);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);


        gettools = new Tools();
        anhxa();
        Intent intent1 = getIntent();
        gettools = (Tools) intent1.getSerializableExtra("aToolsvv");
        edtname.setText(gettools.ten);
        edtpn.setText(gettools.prn);
        edtsn.setText(gettools.sn);
        Picasso.with(this).load(gettools.anh1).into(imageView1);
        Picasso.with(this).load(gettools.anh2).into(imageView2);
        Picasso.with(this).load(gettools.anh3).into(imageView3);


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

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settools = new Tools();
                settools.setId(gettools.getId());
                settools.setTen(edtname.getText().toString());
                settools.setPrn(edtpn.getText().toString());
                settools.setSn(edtsn.getText().toString());
                settools.setAnh1(anh1);
                settools.setAnh2(anh2);
                settools.setAnh3(anh3);
                if (nBound) {

                    nService.updateTools(settools);

                }
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
                anh1 = getStringImage(bitmap1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (requestCode == 998 && resultCode == RESULT_OK && data != null) {
            Uri filepath = data.getData();
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView2.setImageBitmap(bitmap2);
                anh2 = getStringImage(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (requestCode == 997 && resultCode == RESULT_OK && data != null) {
            Uri filepath = data.getData();
            try {
                bitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView3.setImageBitmap(bitmap3);
                anh3 = getStringImage(bitmap3);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private void anhxa() {
        imageView1 = (ImageView) findViewById(R.id.imageViewanh11);
        imageView2 = (ImageView) findViewById(R.id.imageViewanh21);
        imageView3 = (ImageView) findViewById(R.id.imageViewanh31);
        edtname = (EditText) findViewById(R.id.edtname1);
        edtpn = (EditText) findViewById(R.id.edtpn1);
        edtsn = (EditText) findViewById(R.id.edtsn1);
        btnupdate = (Button) findViewById(R.id.btntupdate);
        btnhuy = (Button) findViewById(R.id.btncancel);

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                startActivity(new Intent(this,MainActivity.class));
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
