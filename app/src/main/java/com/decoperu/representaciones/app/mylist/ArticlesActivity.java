package com.decoperu.representaciones.app.mylist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.decoperu.representaciones.app.mylist.model.Source;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesActivity extends AppCompatActivity {

    @BindView(R.id.idResorce)
    TextView idTextView;

    @BindView(R.id.nameResorce)
    TextView nameTextView;

    @BindView(R.id.descriptionResorce)
    TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        Source source =  NewApplication.getInstance().newsApi.getCurrentSource();

        idTextView.setText(source.getId());
        nameTextView.setText(source.getName());
        descriptionTextView.setText(source.getDescription());


    }

}
