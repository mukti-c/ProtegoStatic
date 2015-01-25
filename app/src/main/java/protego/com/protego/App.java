package protego.com.protego;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by muktichowkwale on 19/01/15.
 */
public class App extends Application {

    private void copyFile (InputStream in, OutputStream out) {
        byte[] buffer = new byte[1024];
        int bytesRead;

        try {
            while ((bytesRead = in.read(buffer)) > 0)
                out.write(buffer, 0, bytesRead);
            in.close();
            out.close();

        } catch (IOException ioe) {
            Log.d("App", "Error in copying file.");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize all global variables



        if (GlobalVariables.last100Conn == null) {
            GlobalVariables.last100Conn = new PastConnQueue();
        }
        else {
            // Clears the queue
            GlobalVariables.last100Conn.clear();
        }

        if (GlobalVariables.lastTwoSec == null) {
            GlobalVariables.lastTwoSec = new LastTwoSecQueue();
        }
        else {
            // Clears the queue
            GlobalVariables.lastTwoSec.clear();
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("copiedKDDTrainingDataset", false)) {
            InputStream in = null;
            OutputStream out = null;
            try {
                String appFilesDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
                String filename = "kddreduced.arff";

                in = getApplicationContext().getResources().openRawResource(R.raw.kddreduced);
                File outFile = new File(appFilesDirectory, filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;

                // Mark that tcpdump has been installed
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("copiedKDDTrainingDataset", true);

                editor.commit();
            } catch (IOException e) {
                Log.d ("App", "File failed to copy");
            }
        }
    }
}
