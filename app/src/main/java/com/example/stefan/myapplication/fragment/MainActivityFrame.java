package com.example.stefan.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.stefan.myapplication.R;

public class MainActivityFrame extends Fragment {

    public interface CommunicationFragmentListener {
        public void listenOnCommunication(String content);
    }

    public MainActivityFrame() {
        // Required empty public constructor
    }

    private CommunicationFragmentListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_activity_frame, container, false);

        Button button = (Button) view.findViewById(R.id.b_fragment_comm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.listenOnCommunication("check this out");
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (CommunicationFragmentListener) context;
    }
}
