package com.hafizhamza.duskysolutiontask.Activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hafizhamza.duskysolutiontask.MainActivity;
import com.hafizhamza.duskysolutiontask.Model.DawaiListModel;
import com.hafizhamza.duskysolutiontask.R;
import com.hafizhamza.duskysolutiontask.adapter.DawaiListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class DawaiListActivity extends AppCompatActivity {
    ProgressDialog dialog;
    ProgressBar progressBar;
    LinearLayout linearLayout;
    DawaiListAdapter vehicleListAdapter;
    RequestQueue queue;
     ArrayList<DawaiListModel> array_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dawai_list);
        array_list = new ArrayList<DawaiListModel>();
        linearLayout=(LinearLayout)findViewById(R.id.linearayout);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
       progressBar.setProgressTintList(ColorStateList.valueOf(Color.BLUE));

        final SwipeRefreshLayout swipeContainer;
        final RecyclerView recyclerView;
        recyclerView=findViewById(R.id.recyclerViewBooking);
        swipeContainer=findViewById(R.id.swipeContainerBooking);
        final ArrayList<DawaiListModel> items;
        queue = Volley.newRequestQueue(this);

        //dialog = ProgressDialog.show(NewRequest.this, "Loading", "Please Wait...", true);
        linearLayout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        List<DawaiListModel> vehicleList = new ArrayList<>();
        items = new ArrayList<DawaiListModel>();
        Log.d("Hello","fsf");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //final DawaiListAdapter vehicleListAdapter = new DawaiListAdapter(this, items);
         vehicleListAdapter = new DawaiListAdapter(this, GetEmployee());
        swipeContainer.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(false);
                 getVehicle(items,recyclerView,swipeContainer,vehicleListAdapter);
            }

        });
        getVehicle(items,recyclerView,swipeContainer,vehicleListAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHorizontalScrollBarEnabled(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(vehicleListAdapter);


    }
    private void getVehicle(ArrayList<DawaiListModel> items, final RecyclerView recyclerView, SwipeRefreshLayout swipeContainer, DawaiListAdapter vehicleListAdapter) {
        array_list.clear();
GetEmployee();
             vehicleListAdapter.notifyDataSetChanged();

        recyclerView.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);

                        for (int i = 0; i < recyclerView.getChildCount(); i++) {
                            View v = recyclerView.getChildAt(i);
                            v.setAlpha(0.0f);
                            v.animate().alpha(1.0f)
                                    .setDuration(300)
                                    .setStartDelay(i * 50)
                                    .start();
                        }

                        return true;
                    }
                });
        swipeContainer.setRefreshing(false);

    }



    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent=new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
        return super.onSupportNavigateUp();
    }
    public ArrayList<DawaiListModel> GetEmployee() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://xyz.com/API/categories.php" , null,
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

                                DawaiListModel dawaiListModel = new DawaiListModel(Integer.valueOf(label1), label6, "", label4, label2, label5, label4, "index8", label3, "");
                                array_list.add(dawaiListModel);
                                Log.d("HELLO",label1);
                            }
                            vehicleListAdapter.notifyDataSetChanged();
                            Log.d("Json response", "onResponse: " + jsonObject1.toString());
                            linearLayout.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
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
        return array_list;
    }
}
