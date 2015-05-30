package xars.restaurante.unimagdalena.edu.co.proyectorestaurantes;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Xars on 29/05/2015.
 */
public class Restaurante implements Serializable {

    private String icono;
    private String nombre;
    private int valoracion;
    private String direccion;
    private LatLon coordenadas;
    private List<Horario> horarios;

    public Restaurante() { }

    public Restaurante(String icono, String nombre, int valoracion, String direccion, LatLon coordenadas, List<Horario> horarios) {
        this.icono = icono;
        this.nombre = nombre;
        this.valoracion = valoracion;
        this.direccion = direccion;
        this.coordenadas = coordenadas;
        this.horarios = horarios;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LatLon getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(LatLon coordenadas) {
        this.coordenadas = coordenadas;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "icono='" + icono + '\'' +
                ", nombre='" + nombre + '\'' +
                ", valoracion=" + valoracion +
                ", direccion='" + direccion + '\'' +
                ", coordenadas=" + coordenadas +
                ", horarios=" + horarios +
                '}';
    }

}
