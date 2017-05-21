package com.example.stefan.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stefan.myapplication.fragment.ArticleFragment;
import com.example.stefan.myapplication.fragment.MainActivityFrame;

public class MainActivity extends AppCompatActivity implements MainActivityFrame.CommunicationFragmentListener{

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.left_fragment, new MainActivityFrame());
            transaction.add(R.id.right_fragment, new ArticleFragment());
            transaction.commit();

            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(myToolbar);
        }

        preferences = getPreferences(MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText2);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i(MainActivity.class.toString(), "settings");
                return true;

            case R.id.action_favorite:
                Log.i(MainActivity.class.toString(), "favs");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void listenOnCommunication(String content) {
        ArticleFragment rightFragment = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.right_fragment);
        rightFragment.updateText(content);

        SharedPreferences.Editor editPreferences = preferences.edit();
        int buttonCounter = getButtonCounter() +1;
        editPreferences.putInt(getString(R.string.button_counter), buttonCounter);
        editPreferences.commit();
        TextView buttonCounterTextView = (TextView) findViewById(R.id.button_counter);
        buttonCounterTextView.setText(String.valueOf(buttonCounter));
    }

    private int getButtonCounter()
    {
        return preferences.getInt(getString(R.string.button_counter), 0);
    }
}
