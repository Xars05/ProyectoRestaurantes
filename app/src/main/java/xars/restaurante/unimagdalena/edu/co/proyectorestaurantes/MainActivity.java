package xars.restaurante.unimagdalena.edu.co.proyectorestaurantes;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends ActionBarActivity {

    private JsonTask jTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tareaLarga();
        doWork();
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
            finish();
            startActivity(getIntent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void tareaLarga() {
        try {
            Thread.sleep(4000);
        } catch(InterruptedException e) {}
    }


    private void doWork() {
        try {
            ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            jTask = new JsonTask();
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                jTask.setContext(getBaseContext());
                jTask.execute(new URL("https://www.dropbox.com/s/v15ysbhcouriaq1/restaurantes.json?dl=1"));
//                jTask.execute(new URL("https://www.dropbox.com/s/ne7vd478wujymjr/restaurantes.json?dl=1"));
            }
            else {
                Toast.makeText(this, "Error de Conexion", Toast.LENGTH_LONG).show();
                //tareaLarga();
                //doWork();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
