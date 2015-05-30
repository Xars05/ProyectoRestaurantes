package xars.restaurante.unimagdalena.edu.co.proyectorestaurantes;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ListActivity extends ActionBarActivity {

    private RecyclerView recView;
    private JsonTask jTask;
    private Toast toastGPS;
    private LocationManager locManager;
    private LocationListener locListener;
    private List<Restaurante> restaurantes;
    private FloatingActionButton floatButton;

    private double lat;
    private double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        restaurantes = (ArrayList<Restaurante>) getIntent().getSerializableExtra("Restaurantes");

        actualizarRecycler();

        locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        floatButton = (FloatingActionButton)findViewById(R.id.search_icon);

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarRecycler();
                if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    actualizarPosicion();
                }
                else {
                    toastGPS = Toast.makeText(getApplicationContext(), "GPS Desactivado", Toast.LENGTH_SHORT);
                    toastGPS.show();
                }
            }
        });

        /*
        try {
            ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            recView = (RecyclerView) findViewById(R.id.RecView);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                jTask.setContext(getBaseContext());
                jTask.setRecView(recView);
                jTask.execute(new URL("https://www.dropbox.com/s/v15ysbhcouriaq1/restaurantes.json?dl=1"));
//                jTask.execute(new URL("https://www.dropbox.com/s/ne7vd478wujymjr/restaurantes.json?dl=1"));
            }
            else {
                Toast.makeText(this, "Error de conexión", Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        tareaLarga();

        restaurantes = jTask.getListaRestaurantes();
        if(restaurantes != null) actualizarRecycler();
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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

    private void tareaLarga()
    {
        try {
            Thread.sleep(10000);
        } catch(InterruptedException e) {}
    }

    private void actualizarPosicion() {
        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        guardarPosicion(location);

        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                guardarPosicion(location);
            }
            public void onProviderDisabled(String provider){
                toastGPS = Toast.makeText(getApplicationContext(), "GPS Desactivado", Toast.LENGTH_SHORT);
                toastGPS.show();
            }
            public void onProviderEnabled(String provider){ }
            public void onStatusChanged(String provider, int status, Bundle extras){  }
        };

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 10, locListener);
    }

    private void guardarPosicion(Location loc) {
        if(loc != null)	{
            lat = loc.getLatitude();
            lon = loc.getLongitude();
        }
    }

    private void subActivity(Restaurante rest) {
        Intent intent = new Intent(this, SubActivity.class);
        intent.putExtra("Restaurante", rest);
        startActivity(intent);
    }

    private Restaurante getItemRestaurante(int x) {
        return restaurantes.get(x);
    }

    private void actualizarRecycler() {
        recView = (RecyclerView) findViewById(R.id.RecView);
        recView.setHasFixedSize(true);

        final Adaptador adaptador = new Adaptador((ArrayList) restaurantes);
        adaptador.setContext(this);

        recView.setAdapter(adaptador);

        final GestureDetector mGestureDetector = new GestureDetector(getBaseContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        recView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());

                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
//                        Toast.makeText(MainActivity.this,"The Item Clicked is: "+recyclerView.getChildAdapterPosition(child),Toast.LENGTH_SHORT).show();
                    Restaurante rest = getItemRestaurante(recyclerView.getChildAdapterPosition(child));
                    Toast.makeText(getBaseContext(),"The Item Clicked is: "+rest.getNombre(),Toast.LENGTH_SHORT).show();
                    subActivity(rest);
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });

        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
