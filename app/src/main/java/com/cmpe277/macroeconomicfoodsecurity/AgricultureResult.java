package com.cmpe277.macroeconomicfoodsecurity;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.GraphView;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.cmpe277.macroeconomicfoodsecurity.sqldb.DBController;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
import java.util.HashMap;

public class AgricultureResult extends ListActivity  implements AdapterView.OnItemSelectedListener,View.OnClickListener  {

    String[] countries = {"India", "China", "USA"};
    GraphView graph;
    DBController controller;
    ListAdapter adapter;
    ArrayList<HashMap<String, String>> myList;

    EditText startYearEd;
    EditText endYearEd;
    Button apply;

    String country="";
    String fromYear="";
    String toYear="";
    String contribution_gdp="";
    String credit="";
    String fertilizers="";
    String fertilizers_prod="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture_result);

        country = getIntent().getExtras().getString("country");
        System.out.println(country);
        fromYear = getIntent().getExtras().getString("fromYear");
        System.out.println(fromYear);
        toYear = getIntent().getExtras().getString("toYear");
        System.out.println(toYear);
        contribution_gdp = getIntent().getExtras().getString("contribution_gdp");
        System.out.println(contribution_gdp);
        credit = getIntent().getExtras().getString("credit");
        System.out.println(credit);
        fertilizers = getIntent().getExtras().getString("fertilizers");
        System.out.println(fertilizers);
        fertilizers_prod = getIntent().getExtras().getString("fertilizers_prod");
        System.out.println(fertilizers_prod);

        apply = (Button) findViewById(R.id.apply);
        apply.setOnClickListener(this);
        Spinner spinnerLanguages = findViewById(R.id.spinner_country);
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                countries);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
        spinnerLanguages.setOnItemSelectedListener(this);

        startYearEd =  (EditText) findViewById(R.id.Start_year);
        endYearEd =  (EditText) findViewById(R.id.End_year);


        graph = (GraphView) findViewById(R.id.graph);
        graph.setVisibility(View.VISIBLE);
        graph.onDataChanged(true,true);

        controller = new DBController(this);

        myList = controller.getAllProducts(country, fromYear, toYear, contribution_gdp, credit, fertilizers, fertilizers_prod);
        loadGraph(myList);

    }

    private void loadGraph(ArrayList<HashMap<String, String>> myList) {
        int i = 0;
        if (myList.size() != 0) {

            DataPoint init = new DataPoint(0, 1);
            DataPoint[] dataPoints_gdp = new DataPoint[myList.size()];
            DataPoint[] dataPoints_in = new DataPoint[myList.size()];
            DataPoint[] dataPoints_out = new DataPoint[myList.size()];
            DataPoint[] dataPoints_net = new DataPoint[myList.size()];
            dataPoints_gdp[0] = init;
            try {
                Double max_in =Double.MIN_VALUE;
                Double max_out =Double.MIN_VALUE;
                Double max_net =Double.MIN_VALUE;

                while (i < myList.size()) {


                    if(contribution_gdp.equalsIgnoreCase("Yes")){
                        Double x = Double.valueOf(myList.get(i).get("a"));
                        Double y = Double.valueOf(myList.get(i).get("f"));
                        DataPoint dp = new DataPoint(x, y);
                        dataPoints_gdp[i] = dp;
                    }
                   if(credit.equalsIgnoreCase("Yes")){
                        System.out.println("My In flow INSIDE "+credit);
                        System.out.println("x "+Double.valueOf(myList.get(i).get("a"))+" y: "
                                +Double.valueOf(myList.get(i).get("g")));

                        Double x = Double.valueOf(myList.get(i).get("a"));
                        Double y = Double.valueOf(myList.get(i).get("g"));


                        DataPoint dp = new DataPoint(x, y);
                        dataPoints_in[i] = dp;

                        if(y>max_in){
                            max_in = y;
                        }
                    }
                    System.out.println("Max value of y "+max_in);
                    if(contribution_gdp.equalsIgnoreCase("Yes")){
                        Double x = Double.valueOf(myList.get(i).get("a"));
                        Double y = Double.valueOf(myList.get(i).get("h"));
                        DataPoint dp = new DataPoint(x, y);
                        dataPoints_out[i] = dp;

                        if(y>max_out){
                            max_out = y;
                        }
                    }
                    if(credit.equalsIgnoreCase("Yes")){
                        Double x = Double.valueOf(myList.get(i).get("a"));
                        Double y = Double.valueOf(myList.get(i).get("i"));
                        DataPoint dp = new DataPoint(x, y);
                        dataPoints_net[i] = dp;

                        if(y>max_net){
                            max_net = y;
                        }
                    }

                    i++;
                }

                LineGraphSeries<DataPoint> series_gdp = new LineGraphSeries<>(dataPoints_gdp);
                graph.addSeries(series_gdp);

            } catch (IllegalArgumentException e) {
                Toast.makeText(AgricultureResult.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
        switch (position) {
            case 1:
                Log.d("Macro","China");
                country = "China";
                break;
            case 0:
                Log.d("Macro","India");
                country = "India";
                break;
            case 2:
                Log.d("Macro","USA");
                country = "USA";
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {

        String fromYear = startYearEd.getText().toString();
        String toYear = endYearEd.getText().toString();
        ArrayList<HashMap<String, String>> myListApply;
        myListApply = controller.getAllProducts(country, fromYear, toYear, contribution_gdp, credit, fertilizers, fertilizers_prod);
        loadGraph(myListApply);
    }
}