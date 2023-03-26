package com.cmpe277.macroeconomicfoodsecurity.sqldb;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {
    private static final String LOGCAT = null;

    public static String tableName = "datasheet";
    public static String year = "year";
    public static String gdp = "gdp";
    public static String fdi_inflows = "fdi_inflows";
    public static String fdi_outflows = "fdi_outflows";
    public static String ie_flow = "ie_flow";
    public static String contribution_gdp = "contribution_gdp";
    public static String credit = "credit";
    public static String fertilizer = "fertilizer";
    public static String fertilizer_prod = "fertilizer_prod";
    public static String reserves = "reserves";
    public static String gni = "gni";
    public static String total_debt = "total_debt";
    public static String gni_current = "gni_current";
    public static String country = "country";

    public DBController(Context applicationcontext) {

        super(applicationcontext, "datasheet.db", null, 1);  // creating DATABASE
        Log.d(LOGCAT, "Datasheet DB Created");

    }


    @Override
    public void onCreate(SQLiteDatabase database) {

        String query;

        query = "CREATE TABLE IF NOT EXISTS " + tableName + "( "
                + year + " TEXT, "
                + gdp + " TEXT, "
                + fdi_inflows + " TEXT, "
                + fdi_outflows + " TEXT, "
                + ie_flow + " TEXT, "
                + contribution_gdp + " TEXT, "
                + credit + " TEXT, "
                + fertilizer + " TEXT, "
                + fertilizer_prod + " TEXT, "
                + reserves + " TEXT, "
                + gni + " TEXT, "
                + total_debt + " TEXT, "
                + gni_current + " TEXT, "
                + country + " TEXT)";

        Log.e("create Query", query);
        database.execSQL(query);

    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS " + tableName;
        database.execSQL(query);
        onCreate(database);
    }


    public ArrayList<HashMap<String, String>> getAllProducts(String country, String fromYear, String toYear, String gdp, String fdiinflows, String fdioutflows, String ieflow) {

        if(Integer.parseInt(fromYear)<1960){
            fromYear = "1960";
        }

        if(Integer.parseInt(toYear)>2020){
            toYear = "2020";
        }

        ArrayList<HashMap<String, String>> productList;
        productList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM " + tableName +
                            " where country='"+ country
                            +"' and year >= "+fromYear+" and year <= "+toYear;
        System.out.println(selectQuery);
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            System.out.println("Adding to list as a map");
            do {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("a", cursor.getString(0));
                map.put("b", cursor.getString(1));
                map.put("c", cursor.getString(2));
                map.put("d", cursor.getString(3));
                map.put("e", cursor.getString(4));
                map.put("f", cursor.getString(5));
                map.put("g", cursor.getString(6));
                map.put("h", cursor.getString(7));
                map.put("i", cursor.getString(8));
                map.put("j", cursor.getString(9));
                map.put("k", cursor.getString(10));
                map.put("l", cursor.getString(11));
                map.put("m", cursor.getString(12));
                map.put("n", cursor.getString(13));

                productList.add(map);

               Log.i("dataofList", cursor.getString(0) + ","
                        + cursor.getString(1) + ","
                        + cursor.getString(2) + ","
                        + cursor.getString(3) + ","
                        + cursor.getString(4) + ","
                        + cursor.getString(5) + ","
                        + cursor.getString(6) + ","
                        + cursor.getString(7) + ","
                        + cursor.getString(8) + ","
                        + cursor.getString(9) + ","
                        + cursor.getString(10) + ","
                        + cursor.getString(11) + ","
                        + cursor.getString(12) + ","
                        + cursor.getString(13));

            } while (cursor.moveToNext());
        }
        return productList;

    }


}
