package com.example.stefan.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.stefan.myapplication.fragment.ArticleFragment;
import com.example.stefan.myapplication.fragment.MainActivityFrame;

public class MainActivity extends AppCompatActivity implements MainActivityFrame.CommunicationFragmentListener{

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.left_fragment, new MainActivityFrame());
            transaction.add(R.id.right_fragment, new ArticleFragment());
            transaction.commit();
        }
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
    public void listenOnCommunication(String content) {
        ArticleFragment rightFragment = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.right_fragment);
        rightFragment.updateText(content);
    }
}
