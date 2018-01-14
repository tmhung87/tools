package com.example.bazoka.demojson;

import android.app.ProgressDialog;
import android.content.ComponentName;
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
import android.widget.ProgressBar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UploadNewTool extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3;
    EditText edtname, edtpn, edtsn;
    Button btnthem, btnhuy;
    Bitmap bitmap1, bitmap2, bitmap3,bitmap;
    Bitmap icon;
    String aBoolean;
    UploadNewToolService myService;
    boolean isBound = false;
    ProgressBar progressBar;

    String imagea1 ="iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEwAACxMBAJqcGAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAUPSURBVFiFtZhrbBVFFIC/dkuBUIJQHj6CIFANCRoRjYoPUIn+UEQJKD8UrJpqNMFWTYyGhFL8A0Z8RHloEYpioiIECkJEEysRGnygUZBXFESNoWB8ILVCWX+cM+7c6c7d3cv1JJt798zMmW8e58yZBZEqYDewERjJ/yO9gQe0j6NAqM8hYFS+hlXAj8A/wAn9fQ44o0hg/YEG4FcF+hNoAXbo+1FgeD4D+4G/gVuQ2XtPGx4GaoDgNOCmWGCt2kc34CKgTeEuSTLSrID2KG4CvlHDXwDnFQBXB5xCVudmS39xFjiAC5BlXePoy5ClCYF7MsJNVrjNwABLPxI4kgXOyAIFucHRz1D9VRlslSEbfz/QyynbCBzLCgfiEIeBr7UDIzUK+CpQmtLW1drm4ZiyvcBnGexcaiseVMNPA/fpUwa8rvpXUkJO1/pXxpTN1bJ5CTYmAh04gwmAr4jiUwgsiYEsSTB+h9adEFMWWLZ8kLcpXAgcdwtHANXAOGCFA/lGSsgRWq/BU54PcjLisHuRKNIF0DXkg1ySANmChJKKFLYN5FSF2wOcjZxsO/MBGkNmtFkgzTLXJdg2kN8DJ4FvgbOAM5Ew1ZgE6EIuTgFZiezlTuDWFLYfBbaobRMzH1H7E9MAJkEutiB7A58js3F3WuOOlAM/IPsw0zEbB7nSgbwQ+B24s0A4iMJddSGNA3JnLg4SYKA+WWUUctLsQpKKgiQf5CLgWWSDn9L/dnCfCdQjKZgrw4B9QDvOCVJsyBDYoE8IPK5tGqzyY8BrwEOIxz+vunYkUBdFAnJnbpH+b9KyADioAynXzjcB16nuuAXcCbxPAUmET0qRZbIhQ2RWzJL2RALvFgXsR25I6glcAVwLDCoWGNrZOuA37fBF/BnPM1q2GpgEnFtMEB9cs3b6BFHEX0X8qWIPwCzlPE/dosLNUt0gBVyNzF65B3IMkvZ/SNeQVLAMBa4BxiOpuoGb7dR7ieg87UCyc5/YezYp4YiVbsjd1VyY3Geup9OntOOtWm++VT4AiX2lVn07dUubqdPf6mAfssFnANOAWuCuFDa6E8W/0aqbQxR+4iBTXScqgC+Ri3sdufeSNDIbOeB7IIE2BK63YJoSIBuTIFcgm32qpatEZq8JeBfo42lbr5006/tgJJtZS7TH8kGaBGSpD3IMucnhcOAT7SREgm0IbIuBnKVl65HlNfICXb01DaQd6P+TBQo4TN/NzWshkjBWIEmlCzmNaOZsONNpnLfmgzTZ9TIXcjvitUY+RZJFV2p1IG/rezW5zuCKz1tdyMDSG8jlNuQhxPNAQkInMnuu9ELi3Jv6PlaN5UtOfd5qw/ggG0G8tYNoicarkT0xnY1FToqPLGB0QD7pREIVwP3IUteo3mTM0/X3XkvfA/lw0ALwAfCzNh4C/ISkQjc6nc3XkVXpe62+p0kufd5qz9hSq34l8tluG8BjWmGcFp6vkO1ITFyIfAgKgQOWka3AL6Q/CXzeGiDLebtTfwPwF8jd4Q/EOcxhbyBDJNy0Ihmx+TxsPLg+JZwN6fVWRzYpFyBpufEec4oMRsJMP6fh5drwO+SamVW83mrJQOR2uNkoSpA9EAIfA5fFNKoAnkSWvo2ED98ZIN8C+lplfYkyp0l2oxIFMHeFA8gJsQo5WcwXp1aioH46EhBNShvwjvZ1RHUv+xqegxz+25FpPoFcflYiS17sbLgaSVRC5CDYQRSa+BdID68M+uQ7IgAAAABJRU5ErkJggg==" ;
    String imagea2 ="iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEwAACxMBAJqcGAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAUPSURBVFiFtZhrbBVFFIC/dkuBUIJQHj6CIFANCRoRjYoPUIn+UEQJKD8UrJpqNMFWTYyGhFL8A0Z8RHloEYpioiIECkJEEysRGnygUZBXFESNoWB8ILVCWX+cM+7c6c7d3cv1JJt798zMmW8e58yZBZEqYDewERjJ/yO9gQe0j6NAqM8hYFS+hlXAj8A/wAn9fQ44o0hg/YEG4FcF+hNoAXbo+1FgeD4D+4G/gVuQ2XtPGx4GaoDgNOCmWGCt2kc34CKgTeEuSTLSrID2KG4CvlHDXwDnFQBXB5xCVudmS39xFjiAC5BlXePoy5ClCYF7MsJNVrjNwABLPxI4kgXOyAIFucHRz1D9VRlslSEbfz/QyynbCBzLCgfiEIeBr7UDIzUK+CpQmtLW1drm4ZiyvcBnGexcaiseVMNPA/fpUwa8rvpXUkJO1/pXxpTN1bJ5CTYmAh04gwmAr4jiUwgsiYEsSTB+h9adEFMWWLZ8kLcpXAgcdwtHANXAOGCFA/lGSsgRWq/BU54PcjLisHuRKNIF0DXkg1ySANmChJKKFLYN5FSF2wOcjZxsO/MBGkNmtFkgzTLXJdg2kN8DJ4FvgbOAM5Ew1ZgE6EIuTgFZiezlTuDWFLYfBbaobRMzH1H7E9MAJkEutiB7A58js3F3WuOOlAM/IPsw0zEbB7nSgbwQ+B24s0A4iMJddSGNA3JnLg4SYKA+WWUUctLsQpKKgiQf5CLgWWSDn9L/dnCfCdQjKZgrw4B9QDvOCVJsyBDYoE8IPK5tGqzyY8BrwEOIxz+vunYkUBdFAnJnbpH+b9KyADioAynXzjcB16nuuAXcCbxPAUmET0qRZbIhQ2RWzJL2RALvFgXsR25I6glcAVwLDCoWGNrZOuA37fBF/BnPM1q2GpgEnFtMEB9cs3b6BFHEX0X8qWIPwCzlPE/dosLNUt0gBVyNzF65B3IMkvZ/SNeQVLAMBa4BxiOpuoGb7dR7ieg87UCyc5/YezYp4YiVbsjd1VyY3Geup9OntOOtWm++VT4AiX2lVn07dUubqdPf6mAfssFnANOAWuCuFDa6E8W/0aqbQxR+4iBTXScqgC+Ri3sdufeSNDIbOeB7IIE2BK63YJoSIBuTIFcgm32qpatEZq8JeBfo42lbr5006/tgJJtZS7TH8kGaBGSpD3IMucnhcOAT7SREgm0IbIuBnKVl65HlNfICXb01DaQd6P+TBQo4TN/NzWshkjBWIEmlCzmNaOZsONNpnLfmgzTZ9TIXcjvitUY+RZJFV2p1IG/rezW5zuCKz1tdyMDSG8jlNuQhxPNAQkInMnuu9ELi3Jv6PlaN5UtOfd5qw/ggG0G8tYNoicarkT0xnY1FToqPLGB0QD7pREIVwP3IUteo3mTM0/X3XkvfA/lw0ALwAfCzNh4C/ISkQjc6nc3XkVXpe62+p0kufd5qz9hSq34l8tluG8BjWmGcFp6vkO1ITFyIfAgKgQOWka3AL6Q/CXzeGiDLebtTfwPwF8jd4Q/EOcxhbyBDJNy0Ihmx+TxsPLg+JZwN6fVWRzYpFyBpufEec4oMRsJMP6fh5drwO+SamVW83mrJQOR2uNkoSpA9EAIfA5fFNKoAnkSWvo2ED98ZIN8C+lplfYkyp0l2oxIFMHeFA8gJsQo5WcwXp1aioH46EhBNShvwjvZ1RHUv+xqegxz+25FpPoFcflYiS17sbLgaSVRC5CDYQRSa+BdID68M+uQ7IgAAAABJRU5ErkJggg==";
    String imagea3 ="iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEwAACxMBAJqcGAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAUPSURBVFiFtZhrbBVFFIC/dkuBUIJQHj6CIFANCRoRjYoPUIn+UEQJKD8UrJpqNMFWTYyGhFL8A0Z8RHloEYpioiIECkJEEysRGnygUZBXFESNoWB8ILVCWX+cM+7c6c7d3cv1JJt798zMmW8e58yZBZEqYDewERjJ/yO9gQe0j6NAqM8hYFS+hlXAj8A/wAn9fQ44o0hg/YEG4FcF+hNoAXbo+1FgeD4D+4G/gVuQ2XtPGx4GaoDgNOCmWGCt2kc34CKgTeEuSTLSrID2KG4CvlHDXwDnFQBXB5xCVudmS39xFjiAC5BlXePoy5ClCYF7MsJNVrjNwABLPxI4kgXOyAIFucHRz1D9VRlslSEbfz/QyynbCBzLCgfiEIeBr7UDIzUK+CpQmtLW1drm4ZiyvcBnGexcaiseVMNPA/fpUwa8rvpXUkJO1/pXxpTN1bJ5CTYmAh04gwmAr4jiUwgsiYEsSTB+h9adEFMWWLZ8kLcpXAgcdwtHANXAOGCFA/lGSsgRWq/BU54PcjLisHuRKNIF0DXkg1ySANmChJKKFLYN5FSF2wOcjZxsO/MBGkNmtFkgzTLXJdg2kN8DJ4FvgbOAM5Ew1ZgE6EIuTgFZiezlTuDWFLYfBbaobRMzH1H7E9MAJkEutiB7A58js3F3WuOOlAM/IPsw0zEbB7nSgbwQ+B24s0A4iMJddSGNA3JnLg4SYKA+WWUUctLsQpKKgiQf5CLgWWSDn9L/dnCfCdQjKZgrw4B9QDvOCVJsyBDYoE8IPK5tGqzyY8BrwEOIxz+vunYkUBdFAnJnbpH+b9KyADioAynXzjcB16nuuAXcCbxPAUmET0qRZbIhQ2RWzJL2RALvFgXsR25I6glcAVwLDCoWGNrZOuA37fBF/BnPM1q2GpgEnFtMEB9cs3b6BFHEX0X8qWIPwCzlPE/dosLNUt0gBVyNzF65B3IMkvZ/SNeQVLAMBa4BxiOpuoGb7dR7ieg87UCyc5/YezYp4YiVbsjd1VyY3Geup9OntOOtWm++VT4AiX2lVn07dUubqdPf6mAfssFnANOAWuCuFDa6E8W/0aqbQxR+4iBTXScqgC+Ri3sdufeSNDIbOeB7IIE2BK63YJoSIBuTIFcgm32qpatEZq8JeBfo42lbr5006/tgJJtZS7TH8kGaBGSpD3IMucnhcOAT7SREgm0IbIuBnKVl65HlNfICXb01DaQd6P+TBQo4TN/NzWshkjBWIEmlCzmNaOZsONNpnLfmgzTZ9TIXcjvitUY+RZJFV2p1IG/rezW5zuCKz1tdyMDSG8jlNuQhxPNAQkInMnuu9ELi3Jv6PlaN5UtOfd5qw/ggG0G8tYNoicarkT0xnY1FToqPLGB0QD7pREIVwP3IUteo3mTM0/X3XkvfA/lw0ALwAfCzNh4C/ISkQjc6nc3XkVXpe62+p0kufd5qz9hSq34l8tluG8BjWmGcFp6vkO1ITFyIfAgKgQOWka3AL6Q/CXzeGiDLebtTfwPwF8jd4Q/EOcxhbyBDJNy0Ihmx+TxsPLg+JZwN6fVWRzYpFyBpufEec4oMRsJMP6fh5drwO+SamVW83mrJQOR2uNkoSpA9EAIfA5fFNKoAnkSWvo2ED98ZIN8C+lplfYkyp0l2oxIFMHeFA8gJsQo5WcwXp1aioH46EhBNShvwjvZ1RHUv+xqegxz+25FpPoFcflYiS17sbLgaSVRC5CDYQRSa+BdID68M+uQ7IgAAAABJRU5ErkJggg==";


    @Override
    protected void onStart() {
        super.onStart();

//      goi myservice
        Intent intent = new Intent(this, UploadNewToolService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }
//      tao ket noi service
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName classname, IBinder service) {

            UploadNewToolService.MyBinder myBinder = (UploadNewToolService.MyBinder) service;
            myService = myBinder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_new_tool);



        imageView1 = (ImageView) findViewById(R.id.imageViewanh1);
        imageView2 = (ImageView) findViewById(R.id.imageViewanh2);
        imageView3 = (ImageView) findViewById(R.id.imageViewanh3);
        edtname = (EditText) findViewById(R.id.edtname);
        edtpn = (EditText) findViewById(R.id.edtpn);
        edtsn = (EditText) findViewById(R.id.edtsn);
        btnthem = (Button) findViewById(R.id.btnthem);
        btnhuy = (Button) findViewById(R.id.btnhuy);

//        chon image tu gallery
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

//   chua doc duoc     final Intent intent = new Intent(getApplicationContext(), UploadNewToolService.class);

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtname.getText().toString();
                String pn = edtpn.getText().toString();
                String sn = edtsn.getText().toString();
                if (isBound) {
                    myService.uploaddata(ten, pn, sn, imagea1, imagea2, imagea3);

                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadNewTool.this,MainActivity.class));
                finish();
            }

        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK && data != null) {
            Uri filepath = data.getData();

            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView1.setImageBitmap(bitmap1);
                imagea1 = getStringImage(bitmap1);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (requestCode == 998 && resultCode == RESULT_OK && data != null) {
            Uri filepath = data.getData();
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView2.setImageBitmap(bitmap2);
                imagea2=getStringImage(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 997 && resultCode == RESULT_OK && data != null) {
            Uri filepath = data.getData();
            try {
                bitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView3.setImageBitmap(bitmap3);
                imagea3 = getStringImage(bitmap3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getStringImage(Bitmap bm) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, ba);
        byte[] imagebyte = ba.toByteArray();
        String encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);
        return encode;
    }

}
