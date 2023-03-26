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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link trade#newInstance} factory method to
 * create an instance of this fragment.
 */
public class trade extends Fragment implements View.OnClickListener {

    String[] countries = { "India","China","USA"};

    private String fromYear="1960";
    private String toYear="2020";

    private String country = "India";
    String reserves="";
    String gni="";
    String totalDebt="";
    String gni_curr="";

    Button showData;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public trade() {
        // Required empty public constructor
    }


    public static trade newInstance(String param1, String param2) {
        trade fragment = new trade();
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
        View rootView = inflater.inflate(R.layout.fragment_trade, container, false);
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
                        Log.d("Trade","China");
                        country = "China";
                        break;
                    case 0:
                        Log.d("trade","India");
                        country = "India";
                        break;
                    case 2:
                        Log.d("Trade","USA");
                        country = "USA";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        CheckBox c_gdp = (CheckBox) rootView.findViewById(R.id.reserve);
        c_gdp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reserves = "Yes";
                } else {
                    reserves = "No";
                }
            }
        });

        CheckBox c_in_flow = (CheckBox) rootView.findViewById(R.id.gni);
        c_in_flow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gni = "Yes";
                } else {
                    gni = "No";
                }
            }
        });

        CheckBox c_out_flow = (CheckBox) rootView.findViewById(R.id.tdbt);
        c_out_flow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalDebt = "Yes";
                } else {
                    totalDebt = "No";
                }
            }
        });

        CheckBox c_ie_flow = (CheckBox) rootView.findViewById(R.id.gni_curr);
        c_ie_flow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gni_curr = "Yes";
                } else {
                    gni_curr = "No";
                }
            }
        });

        showData = (Button) rootView.findViewById(R.id.tradeShow);
        showData.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        // implements your things

        Intent intent = new Intent(getActivity(), TradeResult.class);

        intent.putExtra("country", country);
        intent.putExtra("fromYear", fromYear);
        intent.putExtra("toYear", toYear);
        intent.putExtra("reserves", reserves);
        intent.putExtra("gni", gni);
        intent.putExtra("totalDebt", totalDebt);
        intent.putExtra("gni_curr", gni_curr);

        startActivity(intent);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }


}