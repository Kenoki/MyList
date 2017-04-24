package com.decoperu.representaciones.app.mylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.decoperu.representaciones.app.mylist.adapter.SourceAdapter;
import com.decoperu.representaciones.app.mylist.api.NewsApi;
import com.decoperu.representaciones.app.mylist.model.Source;
import com.decoperu.representaciones.app.mylist.model.SourceResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements SourceAdapter.NewsItemListener {

    @BindView(R.id.rcvSources)
    RecyclerView recyclerView;

    SourceAdapter sourceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ButterKnife.bind(this);

        sourceAdapter = new SourceAdapter(new ArrayList<Source>(),this);
        recyclerView.setAdapter(sourceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateSources();
    }

    private void updateSources() {
        AndroidNetworking.get(NewsApi.SOURCES_URL)
                .addQueryParameter("language", "en")
                .setPriority(Priority.LOW)
                .setTag("NEWS")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new Gson();
                        SourceResponse sourceResponse = gson.fromJson(response.toString(), SourceResponse.class);

                        sourceAdapter.replaceData(sourceResponse.getSources());
                        /*
                        try {


                            //sourcesAdapter.setSources(sources);
                            //sourcesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    */

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("NEWS", anError.getLocalizedMessage());
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void newsSelected(Source _source) {
        NewApplication.getInstance().newsApi.setCurrentSource(_source);
        startActivity(new Intent(MainActivity.this, ArticlesActivity.class));
        //Toast.makeText(this, _source.getName(), Toast.LENGTH_SHORT).show();
    }
}
