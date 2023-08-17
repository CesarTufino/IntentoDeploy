package modelo.entidades;

import java.util.ArrayList;
import java.util.List;


public class Cliente {

    private String cedula;
    private String nombre;
    private String apellido;
    private int puntosPorFidelidad;
    private static List<Cliente> clientes = null;

    public Cliente() {
    }

    public Cliente(String cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puntosPorFidelidad = 0;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getPuntosPorFidelidad() {
        return puntosPorFidelidad;
    }

    public void setPuntosPorFidelidad(int puntosPorFidelidad) {
        this.puntosPorFidelidad = puntosPorFidelidad;
    }

    // Reglas del negocio

    public void create(Cliente c) {
        this.getClientes().add(c);
    }

    public List<Cliente> getClientes() {
        if (clientes == null) {
            clientes = new ArrayList<Cliente>();
            Cliente cliente1 = new Cliente("1778569874", "Pérez", "Juan");
            cliente1.setPuntosPorFidelidad(50);
            Cliente cliente2 = new Cliente("1745747478", "Gómez", "Maria");
            cliente2.setPuntosPorFidelidad(100);

            clientes.add(cliente1);
            clientes.add(cliente2);
        }
        return clientes;
    }

    public Cliente getByCedula(String cedula) {
        Cliente c = null;
        List<Cliente> listClientes = this.getClientes();

        for (Cliente cliente : listClientes) {
            if (cliente.getCedula().equals(cedula)) {
                c = cliente;
                break;
            }
        }
        return c;
    }
}
