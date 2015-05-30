package xars.restaurante.unimagdalena.edu.co.proyectorestaurantes;

import java.io.Serializable;

/**
 * Created by Xars on 29/05/2015.
 */
public class Horario implements Serializable {

    private String dia;
    private String hora;

    public Horario() { }

    public Horario(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "dia='" + dia + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }

}
