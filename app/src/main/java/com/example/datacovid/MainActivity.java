package com.example.datacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView sasaranumum,totalnakes,totallansia,totalpublik,vak1,vak2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sasaranumum = findViewById(R.id.sasaranumum);
        totalnakes = findViewById(R.id.totalnakes);
        totallansia = findViewById(R.id.totallansia);
        totalpublik = findViewById(R.id.totalpublik);
        vak1 = findViewById(R.id.vak1);
        vak2 = findViewById(R.id.vak2);

        tampilData();

    }

    private void tampilData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://vaksincovid19-api.vercel.app/api/vaksin";
        JSONObject jsonObject = new JSONObject();
        final String requestBody = jsonObject.toString();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo = new JSONObject(response.toString());
                    String umum = jo.getString("totalsasaran");
                    String nakes = jo.getString("sasaranvaksinsdmk");
                    String lansia = jo.getString("sasaranvaksinlansia");
                    String publik = jo.getString("sasaranvaksinpetugaspublik");
                    String vaksin1 = jo.getString("vaksinasi1");
                    String vaksin2 = jo.getString("vaksinasi2");

                    sasaranumum.setText(umum);
                    totalnakes.setText(nakes);
                    totallansia.setText(lansia);
                    totalpublik.setText(publik);
                    vak1.setText(vaksin1);
                    vak2.setText(vaksin2);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Data Gagal Dimuat ",Toast.LENGTH_SHORT).show();
            }
        }
        );
        queue.add(stringRequest);
    }
}