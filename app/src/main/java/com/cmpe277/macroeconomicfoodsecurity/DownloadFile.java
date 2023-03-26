package com.cmpe277.macroeconomicfoodsecurity;


        import android.os.AsyncTask;
        import android.os.Environment;
        import android.util.Log;

        import java.io.BufferedInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLConnection;

public class DownloadFile extends AsyncTask {

    String root = Environment.getExternalStorageDirectory().toString();

    @Override
    protected Object doInBackground(Object[] objects) {

        Log.i("DownloadFile", "doInBackground");

        for(int i =0;i< objects.length;i++){
            String link = objects[i].toString();

            String filename = link.substring((link.lastIndexOf('/')+1),link.length());

            try {
                URL url = new URL(link);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();


                Log.i("DownloadFile","Downloading "+link);
                Log.i("DownloadFile","Filename Before "+filename);


                InputStream inputStream = new BufferedInputStream(url.openStream(),8192);
                OutputStream outputStream = new FileOutputStream(root+"/Download/"+"datasheet1.csv");

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    outputStream.write(data, 0, count);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        Log.i("DownloadFile","After download code");
        return null;
    }
}
