package com.example.bazoka.demojson;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UploadNewToolService extends Service {
    private final IBinder mbinder = new MyBinder();
    String aBoolean;
    String urlup = "https://1luutru.000webhostapp.com/setdata.php";


    // Bắt đầu một Service
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("ServiceDemo", "Đã gọi onBind()");
        // trả về đối tượng binder cho ActivityMain
        return mbinder;
    }

    // Kết thúc một Service
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("ServiceDemo", "Đã gọi onUnBind()");
        return super.onUnbind(intent);
    }


    public class MyBinder extends Binder {

        // phương thức này trả về đối tượng UploadNewToolService
        UploadNewToolService getService() {

            return UploadNewToolService.this;
        }
    }

    public void uploaddata(final String ten, final String pn, final String sn, final String anh1, final String anh2, final String anh3) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlup,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s = response.trim();

                        if (s.equalsIgnoreCase("Error")) {
                            Toast.makeText(getApplicationContext(), "Setdata Error", Toast.LENGTH_SHORT).show();

                            aBoolean = "SetData Error";

                        } else {
                            Toast.makeText(getApplicationContext(), "Setdata Success", Toast.LENGTH_SHORT).show();

                            aBoolean = "SetData Success";
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            addNotification();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error + "", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("TEN", ten);
                params.put("PRN", pn);
                params.put("SN", sn);
                params.put("ANH1", anh1);
                params.put("ANH2", anh2);
                params.put("ANH3", anh3);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        Log.d("ServiceDemo", "Thanh cong");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ServiceDemo", "Da goi onDestroy");
    }
    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.tools)
                        .setLargeIcon( BitmapFactory.decodeResource(getResources(),
                                R.drawable.tools))
                        .setContentTitle("Tools")
                        .setContentText("Upload New Tool Success");

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
