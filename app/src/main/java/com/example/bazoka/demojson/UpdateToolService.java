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

public class UpdateToolService extends Service {

    String urlup = "https://1luutru.000webhostapp.com/update.php";
    // Binder given to clients
    private final IBinder mBinder = new UpdateBinder();
    boolean check=false;
    // Random number generator

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class UpdateBinder extends Binder {
        UpdateToolService getService() {
            // Return this instance of LocalService so clients can call public methods
            return UpdateToolService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** method for clients */
    public void updateTools(final Tools t) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlup, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response.trim();

                if (s.equalsIgnoreCase("Loi")) {
                    Toast.makeText(getApplicationContext(), "Update Tools Error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Update Tools Success", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    addNotification();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error + "", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("TEN", t.ten);
                params.put("PRN", t.prn);
                params.put("SN", t.sn);
                params.put("ANH1", t.anh1);
                params.put("ANH2", t.anh2);
                params.put("ANH3", t.anh3);
                params.put("ID", String.valueOf(t.id));

                return params;
            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        requestQueue1.add(stringRequest);
    }
    public void toast(){
        Toast.makeText(this, "chay service", Toast.LENGTH_SHORT).show();
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.tools)
                        .setLargeIcon( BitmapFactory.decodeResource(getResources(),
                                R.drawable.tools))
                        .setContentTitle("Tools")
                        .setContentText("Update Tool Success");

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}