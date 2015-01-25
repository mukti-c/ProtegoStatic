package protego.com.protego;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by chanijindal on 24/01/15.
 */
public class CreateLogFile {

public static StringBuilder logData;

    public static boolean makeFile(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String path = prefs.getString("selectedDirectory","/mnt/sdcard");
        File logFile = new File(path + "/Activitylog.txt");

        try {
            logFile.createNewFile();

            FileWriter fileWriter = new FileWriter(logFile.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(logData.toString());
            bufferedWriter.close();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;


        }


    }




}
