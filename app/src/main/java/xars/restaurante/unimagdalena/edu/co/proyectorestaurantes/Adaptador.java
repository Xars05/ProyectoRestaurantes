package xars.restaurante.unimagdalena.edu.co.proyectorestaurantes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Xars on 29/05/2015.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.RestaurantesViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private ArrayList<Restaurante> datos;
    private static Context context;
    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the viaew under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    public void setContext(Context context) {
        this.context = context;
    }

    public static class RestaurantesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int holderid;
        private TextView lblNombre;
        private TextView lblValoracion;
        private TextView lblDireccion;
        private ImageView imgImage;

        public RestaurantesViewHolder(View itemView) {
            super(itemView);

            lblNombre = (TextView)itemView.findViewById(R.id.LblNombre);
            lblValoracion = (TextView)itemView.findViewById(R.id.LblValoracion);
            lblDireccion = (TextView)itemView.findViewById(R.id.LblDireccion);
            imgImage = (ImageView) itemView.findViewById(R.id.image);
        }

        public RestaurantesViewHolder(View itemView,int ViewType,Context c) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            context = c;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

            if(ViewType == TYPE_ITEM) {
                holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            }
            else{                                                            // Creating Image view object from header.xml for profile pic
                holderid = 0;                                                // Setting holder id = 0 as the object being populated are of type header view
            }
        }

        public void bindRestaurante(Restaurante r) {
            lblNombre.setText(r.getNombre());
            lblValoracion.setText(String.valueOf(r.getValoracion()));
            lblDireccion.setText(r.getDireccion());
//            imgImage.setImageResource(R.drawable.imagen2);
            int id = context.getResources().getIdentifier(r.getIcono(), "drawable", context.getPackageName());
            imgImage.setImageResource(id);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public Adaptador(ArrayList<Restaurante> datos) {
        this.datos = datos;
    }

    @Override
    public RestaurantesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_restaurante, viewGroup, false);

        itemView.setOnClickListener(this);
        //android:background="?android:attr/selectableItemBackground"

        RestaurantesViewHolder rvh = new RestaurantesViewHolder(itemView);

        return rvh;
    }

    @Override
    public void onBindViewHolder(RestaurantesViewHolder viewHolder, int pos) {
        Restaurante item = datos.get(pos);

        viewHolder.bindRestaurante(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

}
