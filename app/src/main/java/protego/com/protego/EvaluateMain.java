package protego.com.protego;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class EvaluateMain extends Activity {

    EditText ftrain, feval;
    Button train, eval;
    TextView summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_main);

        ftrain = (EditText) findViewById(R.id.ftrain);
        feval = (EditText) findViewById(R.id.feval);
        train = (Button) findViewById(R.id.train);
        eval = (Button) findViewById(R.id.eval);
        summary = (TextView) findViewById(R.id.summary);

        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String readfile = ftrain.getText().toString();
                int readflag;

                Classify classifier = new Classify();
                readflag = classifier.Train(readfile);

                if(readflag == 1)
                    Toast.makeText(getApplicationContext(), "Classifier built", Toast.LENGTH_SHORT).show();
                else if (readflag == -1)
                    Toast.makeText(getApplicationContext(), "Error: Could not build.",Toast.LENGTH_SHORT).show();
                else if (readflag == 0)
                    Toast.makeText(getApplicationContext(), "Error: File not found",Toast.LENGTH_SHORT).show();
            }
        });

        eval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String readfile = feval.getText().toString();
                Classify classifier = new Classify();
                String text = classifier.Evaluate(readfile);
                summary.setText(text);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}