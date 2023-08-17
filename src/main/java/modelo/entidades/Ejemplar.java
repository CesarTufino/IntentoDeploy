package modelo.entidades;

import java.util.ArrayList;
import java.util.List;


public class Ejemplar {

    private String codigoEjemplar;
    private Boolean estadoDisponibilidad;
    private double costoPorDia;
    private Pelicula pelicula;
    private List<Alquiler> alquileres = new ArrayList<Alquiler>();

    private static List<Ejemplar> ejemplares = null;

    public Ejemplar() {
    }

    public Ejemplar(String codigoEjemplar, Boolean estadoDisponibilidad, double costoPorDia, Pelicula pelicula) {
        this.codigoEjemplar = codigoEjemplar;
        this.estadoDisponibilidad = estadoDisponibilidad;
        this.costoPorDia = costoPorDia;
        this.pelicula = pelicula;
    }

    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }

    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }

    public Boolean getEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public void setEstadoDisponibilidad(Boolean estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    public double getCostoPorDia() {
        return costoPorDia;
    }

    public void setCostoPorDia(double costoPorDia) {
        this.costoPorDia = costoPorDia;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<Alquiler> alquileres) {
        this.alquileres = alquileres;
    }

    // Reglas del negocio
    public void create(Ejemplar e) {
        this.getEjemplares().add(e);
    }
    public List<Ejemplar> getEjemplares() {
        if (ejemplares == null) {
            Pelicula modeloPelicula = new Pelicula();
            ejemplares = new ArrayList<Ejemplar>();
            ejemplares.add(new Ejemplar("E001", true, 4.99, modeloPelicula.getPeliculas().get(0)));
            ejemplares.add(new Ejemplar("E002", true, 7, modeloPelicula.getPeliculas().get(1)));
            ejemplares.add(new Ejemplar("E003", true, 7, modeloPelicula.getPeliculas().get(1)));
            ejemplares.add(new Ejemplar("E004", true, 6, modeloPelicula.getPeliculas().get(2)));
        }
        return ejemplares;
    }

    public Ejemplar getByCodigoEjemplar(String codigoEjemplar) {
        Ejemplar e = null;
        List<Ejemplar> listEjemplares = this.getEjemplares();

        for (Ejemplar ejemplar : listEjemplares){
            if (ejemplar.getCodigoEjemplar().equals(codigoEjemplar)) {
                e = ejemplar;
                break;
            }
        }
        return e;
    }
}
