package com.hafizhamza.duskysolutiontask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        GetData();
    }

    void GetData()
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://dawaihazir.com/API/categories.php" , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject1.getJSONArray("Categories");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                String label1=obj.getString("categoryid");
                                String label2=obj.getString("categoryname");
                                String label3=obj.getString("categoryimage");
                                String label4=obj.getString("vendorid");
                                String label5=obj.getString("created_at");
                                String label6=obj.getString("categorystatus");


                                Log.d("HELLO",label1);
                            }
                            Log.d("Json response", "onResponse: " + jsonObject1.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("GetEmptypeerror2",error.toString());

            }
        });
        queue.add(request);
    }
    public void check(View view) {
        GetData();
    }
}
