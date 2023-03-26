package com.cmpe277.macroeconomicfoodsecurity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;


public class agriculture extends Fragment implements View.OnClickListener {

    String[] countries = { "India","China","USA"};

    private String fromYear="1960";
    private String toYear="2020";

    private String country = "India";
    String contribution_gdp="";
    String credit="";
    String fertilizers="";
    String fertilizers_prod="";

    Button showData;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public agriculture() {
        // Required empty public constructor
    }


    public static agriculture newInstance(String param1, String param2) {
        agriculture fragment = new agriculture();
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
        View rootView = inflater.inflate(R.layout.fragment_agriculture, container, false);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner_country);

        ArrayAdapter ad = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                countries);

        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        spinner.setAdapter(ad);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                switch (position) {
                    case 1:
                        Log.d("Agri","China");
                        country = "China";
                        break;
                    case 0:
                        Log.d("Agri","India");
                        country = "India";
                        break;
                    case 2:
                        Log.d("Agri","USA");
                        country = "USA";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });

        CheckBox c_gdp = (CheckBox) rootView.findViewById(R.id.CGDP);
        c_gdp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    contribution_gdp = "Yes";
                } else {
                    contribution_gdp = "No";
                }
            }
        });

        CheckBox c_in_flow = (CheckBox) rootView.findViewById(R.id.credit);
        c_in_flow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    credit = "Yes";
                } else {
                    credit = "No";
                }
            }
        });

        CheckBox c_out_flow = (CheckBox) rootView.findViewById(R.id.fert);
        c_out_flow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fertilizers = "Yes";
                } else {
                    fertilizers = "No";
                }
            }
        });

        CheckBox c_ie_flow = (CheckBox) rootView.findViewById(R.id.fert_prod);
        c_ie_flow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fertilizers_prod = "Yes";
                } else {
                    fertilizers_prod = "No";
                }
            }
        });

        showData = (Button) rootView.findViewById(R.id.agriShow);
        showData.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        // implements your things

        Intent intent = new Intent(getActivity(), AgricultureResult.class);

        intent.putExtra("country", country);
        intent.putExtra("fromYear", fromYear);
        intent.putExtra("toYear", toYear);
        intent.putExtra("contribution_gdp", contribution_gdp);
        intent.putExtra("credit", credit);
        intent.putExtra("fertilizers", fertilizers);
        intent.putExtra("fertilizers_prod", fertilizers_prod);

        startActivity(intent);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }


}