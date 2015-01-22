package protego.com.protego;

import android.app.Application;

/**
 * Created by muktichowkwale on 19/01/15.
 */
public class App extends Application {

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
    }
}
