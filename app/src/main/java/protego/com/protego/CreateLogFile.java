package protego.com.protego;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateLogFile {
    static String path;
public static StringBuilder logData= new StringBuilder();

    public static boolean makeFile(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        path = GlobalVariables.chosen_Dir;
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
