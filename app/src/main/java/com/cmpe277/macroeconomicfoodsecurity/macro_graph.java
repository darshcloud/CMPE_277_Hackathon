package com.cmpe277.macroeconomicfoodsecurity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.cmpe277.macroeconomicfoodsecurity.sqldb.DBController;
import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;
import java.util.HashMap;


public class macro_graph extends Fragment {

    GraphView graph;
    DBController controller;
    ListAdapter adapter;
    ArrayList<HashMap<String, String>> myList;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public macro_graph() {
        // Required empty public constructor
    }


    public static macro_graph newInstance(String param1, String param2) {
        macro_graph fragment = new macro_graph();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_macro, container, false);

        return rootView;
    }
}