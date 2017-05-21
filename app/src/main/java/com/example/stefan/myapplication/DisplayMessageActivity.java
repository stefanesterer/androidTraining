package com.example.stefan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.stefan.myapplication.fragment.ArticleFragment;
import com.example.stefan.myapplication.fragment.HeadlinesFragment;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);

        if (findViewById(R.id.fragment_layout) != null) {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout, new HeadlinesFragment()).commit();
            }
        }

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.activity_display_message_toolbar);
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void switchFragment(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, new ArticleFragment());
        transaction.addToBackStack(null);
        transaction.commit();

    }


}
