package modelo.entidades;

import modelo.bd.BdConexion;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AlquilerTest {

    private static Alquiler modeloAlquiler;
    private static Cliente modeloCliente;
    private static Ejemplar modeloEjemplar;

    @BeforeClass
    public static void setUpClass() {
        modeloAlquiler = new Alquiler();
        modeloCliente = new Cliente();
        modeloEjemplar = new Ejemplar();
    }
    @Test
    public void given_new_cliente_when_client_rents_then_increase_loyalty_points() {
        Cliente clienteQueAlquila = new Cliente("1752995819","Matias","Andrade");
        List<Ejemplar> ejemplaresAlquilados = new ArrayList<Ejemplar>();
        ejemplaresAlquilados.add(modeloEjemplar.getEjemplares().get(0));
        modeloAlquiler.alquilar(3,ejemplaresAlquilados,clienteQueAlquila);
        assertEquals(5, clienteQueAlquila.getPuntosPorFidelidad());
    }

    @Test
    public void given_loyalty_points_when_client_uses_loyalty_points_then_decrease_loyalty_points_and_give_discount() {
        Cliente clienteQueAlquila = new Cliente("1752995819","Matias","Andrade");
        clienteQueAlquila.setPuntosPorFidelidad(70);
        List<Ejemplar> ejemplaresAlquilados = new ArrayList<Ejemplar>();
        ejemplaresAlquilados.add(modeloEjemplar.getEjemplares().get(0));
        Alquiler alquiler = modeloAlquiler.alquilarFidelidad(3,ejemplaresAlquilados,clienteQueAlquila);
        assertEquals(11.98, alquiler.getPrecioTotal(),0.01);
        assertEquals(25, clienteQueAlquila.getPuntosPorFidelidad());
    }

    @Test
    public void given_client_with_max_loyalty_points_when_client_rents_then_not_increase_loyalty_points() {
        Cliente clienteQueAlquila = new Cliente("1752995819","Matias","Andrade");
        clienteQueAlquila.setPuntosPorFidelidad(100);
        List<Ejemplar> ejemplaresAlquilados = new ArrayList<Ejemplar>();
        ejemplaresAlquilados.add(modeloEjemplar.getEjemplares().get(0));
        modeloAlquiler.alquilar(3,ejemplaresAlquilados,clienteQueAlquila);
        assertEquals(100, clienteQueAlquila.getPuntosPorFidelidad());
    }

    @Test
    public void given_damaged_copy_when_client_returns_then_decrease_loyalty_points() {
        Cliente clienteQueDevuelve = new Cliente("1752995819","Matias","Andrade");
        clienteQueDevuelve.setPuntosPorFidelidad(100);
        List<Ejemplar> ejemplaresAlquilados = new ArrayList<Ejemplar>();
        ejemplaresAlquilados.add(modeloEjemplar.getEjemplares().get(0));
        Alquiler alquiler = modeloAlquiler.alquilar(3,ejemplaresAlquilados,clienteQueDevuelve);
        modeloAlquiler.devolver(alquiler, ejemplaresAlquilados, clienteQueDevuelve,true);
        assertEquals(0, clienteQueDevuelve.getPuntosPorFidelidad());
    }

    @Test
    public void test_bd(){
        Connection cnn = BdConexion.getConexion();
        boolean bandera = false;
        if (cnn != null){
            bandera = true;
        }
        assertTrue(bandera);
    }


}