package xars.restaurante.unimagdalena.edu.co.proyectorestaurantes;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xars on 29/05/2015.
 */
public class JsonTask extends AsyncTask<URL, Void, List<Restaurante>> implements Serializable {
    private HttpURLConnection connnection;
    private Context context;
    private List<Restaurante> listaRestaurantes;

    private RecyclerView recView;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setRecView(RecyclerView recView) {
        this.recView = recView;
    }

    public List<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }

    @Override
    protected List<Restaurante> doInBackground(URL... urls) {
        List<Restaurante> restaurantes = null;

        try {

            // Establecer la conexión
            connnection = (HttpURLConnection) urls[0].openConnection();
            connnection.setConnectTimeout(15000);
            connnection.setReadTimeout(10000);

            // Obtener el estado del recurso
            int statusCode = connnection.getResponseCode();

            if (statusCode != 200) {


            } else {

                // Parsear el flujo con formato JSON
                InputStream in = new BufferedInputStream(connnection.getInputStream());

                // JsonAnimalParser parser = new JsonAnimalParser();
                GsonRestauranteParser parser = new GsonRestauranteParser();

                restaurantes = parser.leerFlujoJson(in);


            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            connnection.disconnect();
            listaRestaurantes = restaurantes;
        }

        return restaurantes;

    }

    @Override
    protected void onPostExecute(List<Restaurante> restaurantes) {
        if(restaurantes!=null) {
            Intent intent = new Intent(context, ListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Restaurantes", (java.io.Serializable) restaurantes);
            context.startActivity(intent);
        }else{
            Toast.makeText(
                    context,
                    "Ocurrió un error de Parsing Json",
                    Toast.LENGTH_SHORT)
                    .show();
        }

    }

}