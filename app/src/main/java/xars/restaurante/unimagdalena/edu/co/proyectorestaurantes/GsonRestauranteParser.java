package xars.restaurante.unimagdalena.edu.co.proyectorestaurantes;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xars on 29/05/2015.
 */
public class GsonRestauranteParser {

    public List<Restaurante> leerFlujoJson(InputStream in) throws IOException {

        // Nueva instancia de la clase Gson
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Restaurante> restaurantes = new ArrayList<Restaurante>();

        // Iniciar el array
        reader.beginArray();

        while (reader.hasNext()) {
            // Lectura de objetos
            Restaurante restaurante = gson.fromJson(reader, Restaurante.class);
            restaurantes.add(restaurante);
        }

        reader.endArray();
        reader.close();

        return restaurantes;

    }

}