package controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Alquiler;
import modelo.entidades.Cliente;
import modelo.entidades.Ejemplar;
import modelo.entidades.Pelicula;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/DevolverController")
public class DevolverController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DevolverController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.ruteador(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.ruteador(request, response);
    }

    private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ruta = (request.getParameter("ruta") == null) ? "inicio" : request.getParameter("ruta");
        switch (ruta) {
            case "inicio":
                this.inicio(request, response);
                break;
            case "registrar":
                this.registrar(request, response);
                break;
            case "error":
                break;
            default:
                break;
        }
    }

    private void inicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente modeloCliente = new Cliente();
        Pelicula modeloPelicula = new Pelicula();
        Ejemplar modeloEjemplar = new Ejemplar();
        Alquiler modeloAlquiler = new Alquiler();

        request.setAttribute("clientes", modeloCliente.getClientes());
        request.setAttribute("peliculas", modeloPelicula.getPeliculas());
        request.setAttribute("ejemplares", modeloEjemplar.getEjemplares());
        request.setAttribute("alquileres", modeloAlquiler.getAlquileres());
        request.getRequestDispatcher("jsp/devolucionFormulario.jsp").forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedulaCliente");
        Cliente modeloCliente = new Cliente();
        Cliente cliente = modeloCliente.getByCedula(cedula);

        List<Ejemplar> ejemplaresDevueltos = new ArrayList<>();

        Ejemplar modeloEjemplar = new Ejemplar();

        for (Ejemplar ejemplar : modeloEjemplar.getEjemplares()) {
            String check = request.getParameter(ejemplar.getCodigoEjemplar());
            if (check != null) {
                ejemplaresDevueltos.add(ejemplar);
            }
        }
        Alquiler modeloAlquiler = new Alquiler();

        boolean tienePercanse = request.getParameter("percance")!=null;

        modeloAlquiler.devolver(ejemplaresDevueltos, cliente, tienePercanse);

        response.sendRedirect("MenuController?ruta=inicio");
    }

}