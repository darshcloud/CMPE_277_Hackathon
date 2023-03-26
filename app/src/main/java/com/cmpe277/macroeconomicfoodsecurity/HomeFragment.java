package com.cmpe277.macroeconomicfoodsecurity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cmpe277.macroeconomicfoodsecurity.sqldb.DBController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class HomeFragment extends Fragment implements View.OnClickListener  {


    private static final int RESULT_OK = -1;

    DBController controller;
    Button addToDatabase;
    public static final int requestcode = 1;
    String persona="researcher";

    ImageButton reseacher;
    ImageButton officer;

    String datasheet ="https://docs.google.com/spreadsheets/d/1hkPHIxzebksd6abBINt00IN-sYjCLOSx0sfE7KK4LIA/gviz/tq?tqx=out:csv&sheet=hackathonData";
    String[] links = {datasheet};



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        addToDatabase = (Button) rootView.findViewById(R.id.saveToDB);
        addToDatabase.setOnClickListener(this);

         reseacher = (ImageButton) rootView.findViewById(R.id.user);
        reseacher.setOnClickListener(this);

         officer = (ImageButton) rootView.findViewById(R.id.officer);
         officer.setOnClickListener(this);
         final Context context = getContext();

         controller = new DBController(getContext());



        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Log.i("MainActivity","Pressed on Download Button");
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.execute(links);


        return rootView;
    }

    @Override
    public void onClick(View v) {


        int id = v.getId();

        switch (id){
            case R.id.saveToDB:
                Log.i("MacroResult","After press on upload");

                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                //fileintent.setType("text/csv");
                fileintent.addCategory(Intent.CATEGORY_OPENABLE);
                fileintent.setType("*/*");
                Log.i("MacroResult","After file intent");

                try {
                    startActivityForResult(fileintent, requestcode);
                } catch (ActivityNotFoundException e) {
                    System.out.println("No activity can handle picking a file. Showing alternatives.");
                }

                break;
            case R.id.user:
                persona ="researcher";
                break;
            case R.id.officer:
                persona ="officer";
                break;
        }


    }
   @Deprecated
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("MacroResult","inside on Activity Result");


        if (data == null)

            return;
        switch (requestCode) {

            case requestcode:

                String filepath = data.getData().getPath();
                Log.i("File path", filepath);


                String[] findFileName = filepath.split("/");
                int n = findFileName.length;
                String selectedFileCSV = findFileName[n-1];
                String selectedFile = selectedFileCSV.substring(0,selectedFileCSV.length()-4);

                System.out.println("Selected File Name "+selectedFile);

                if (filepath.contains("/root_path"))
                    filepath = filepath.replace("/root_path", "");


                Log.i("New File path", filepath);
                controller = new DBController(getContext());
                SQLiteDatabase db = controller.getWritableDatabase();

                db.execSQL("delete from " + DBController.tableName);

                try {

                    if (resultCode == RESULT_OK) {
                        Log.i("RESULT CODE", "OK");
                        try {

                            ContentValues contentValues = new ContentValues();
                            db.beginTransaction();

                            File dir = new File("/storage/emulated/0/Download/datasheet1.csv", "");

                            BufferedReader br = null;
                            try {
                                String sCurrentLine;
                                br = new BufferedReader(new FileReader(dir));
                                while ((sCurrentLine = br.readLine()) != null) {
                                    Log.i("line ", sCurrentLine);
                                    String[] str = sCurrentLine.split(",", 14);



                                    String year = str[0].replaceAll("\"", "");
                                    String gdp = str[1].replaceAll("\"", "");
                                    String fdi_inflows = str[2].replaceAll("\"", "");
                                    String fdi_outflows = str[3].replaceAll("\"", "");
                                    String ie_flow = str[4].replaceAll("\"", "");
                                    String contribution_gdp = str[5].replaceAll("\"", "");
                                    String credit = str[6].replaceAll("\"", "");
                                    String fertilizer = str[7].replaceAll("\"", "");
                                    String fertilizer_prod = str[8].replaceAll("\"", "");
                                    String reserves = str[9].replaceAll("\"", "");
                                    String gni = str[10].replaceAll("\"", "");
                                    String total_debt = str[11].replaceAll("\"", "");
                                    String gni_current = str[12].replaceAll("\"", "");
                                    String country = str[13].replaceAll("\"", "");


                                    contentValues.put(DBController.year, year);
                                    contentValues.put(DBController.gdp, gdp);
                                    contentValues.put(DBController.fdi_inflows, fdi_inflows);
                                    contentValues.put(DBController.fdi_outflows, fdi_outflows);
                                    contentValues.put(DBController.ie_flow, ie_flow);
                                    contentValues.put(DBController.contribution_gdp, contribution_gdp);
                                    contentValues.put(DBController.credit, credit);
                                    contentValues.put(DBController.fertilizer, fertilizer);
                                    contentValues.put(DBController.fertilizer_prod, fertilizer_prod);
                                    contentValues.put(DBController.reserves, reserves);
                                    contentValues.put(DBController.gni, gni);
                                    contentValues.put(DBController.total_debt, total_debt);
                                    contentValues.put(DBController.gni_current, gni_current);
                                    contentValues.put(DBController.country, country);


                                    db.insert(DBController.tableName, null, contentValues);

                                    System.out.println("Successfully Updated Database.");
                                    Log.i("Import", "Successfully Updated Database.");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (br != null)br.close();
                                } catch (IOException ex) {
                                    Log.e("IOException", ex.getMessage().toString());
                                }
                            }

                            db.setTransactionSuccessful();
                            db.endTransaction();

                        } catch (SQLException e) {
                            Log.i("SQLError", e.getMessage().toString());
                        }
                    } else {
                        Log.i("RESULT CODE", "InValid");
                        if (db.inTransaction())

                            db.endTransaction();
                        Toast.makeText(getContext(), "Only CSV files allowed.", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.getMessage().toString());
                    if (db.inTransaction())
                        db.endTransaction();
                    Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
        }
    }
}