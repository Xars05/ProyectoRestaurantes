package xars.restaurante.unimagdalena.edu.co.proyectorestaurantes;

import java.io.Serializable;

/**
 * Created by Xars on 29/05/2015.
 */
public class LatLon implements Serializable {

    private double lat;
    private double lon;

    public LatLon() { }

    public LatLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "LatLon{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }

}