package xars.restaurante.unimagdalena.edu.co.proyectorestaurantes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;


public class SubActivity extends ActionBarActivity {

    private TextView lblValoracion;
    private TextView lblDireccion;
    private ImageView imageView;

    private TextView aux;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        imageView = (ImageView)findViewById(R.id.image);
        lblValoracion = (TextView)findViewById(R.id.LblValoracion);
        lblDireccion = (TextView)findViewById(R.id.LblDireccion);

        Intent intent = getIntent();
        Restaurante restaurante = (Restaurante) intent.getSerializableExtra("Restaurante");
        getSupportActionBar().setTitle("NombreApp - " + restaurante.getNombre());

        lblValoracion.setText(String.valueOf(restaurante.getValoracion()));
        lblDireccion.setText(restaurante.getDireccion());

        int i=0;
        for(Horario horario:restaurante.getHorarios()) {
            i++;
            aux = (TextView)findViewById(this.getResources().getIdentifier(horario.getDia(), "id", this.getPackageName()));
            aux.setText(horario.getDia());
            aux = (TextView)findViewById(this.getResources().getIdentifier("Hora"+i, "id", this.getPackageName()));
            aux.setText(horario.getHora());
        }

        int id = this.getResources().getIdentifier(restaurante.getIcono(), "drawable", this.getPackageName());
        imageView.setImageResource(id);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
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
