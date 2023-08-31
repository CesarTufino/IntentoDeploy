package controlador;

import jakarta.servlet.http.HttpSession;
import modelo.entidades.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AlquilerController")
public class AlquilerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AlquilerController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.ruteador(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.ruteador(request, response);
    }

    private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogeado");

        if (usuario == null) {
            response.sendRedirect("LoginController?ruta=inicio");
            return;
        }

        String ruta = (request.getParameter("ruta") == null) ? "inicio" : request.getParameter("ruta");
        switch (ruta) {
            case "inicio":
                if(usuario.getEsGerente()){
                    response.sendRedirect("MenuController?ruta=inicioGerente");
                }else{
                    this.inicio(request, response);
                }
                break;
            case "registrar":
                if(usuario.getEsGerente()){
                    response.sendRedirect("MenuController?ruta=inicioGerente");
                }else{
                    this.registrar(request, response);
                }
                break;
            case "error":
                this.mostrarError(request, response);
                break;
            case "listar":
                if(usuario.getEsGerente()){
                    this.enlistarAlquileresNoDevueltos(request, response);
                }else{
                    response.sendRedirect("MenuController?ruta=inicioCajero");
                }
                break;
            default:
                break;
        }
    }

    private void inicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cliente modeloCliente = new Cliente();
        Pelicula modeloPelicula = new Pelicula();
        request.setAttribute("clientes", modeloCliente.getClientes());
        request.setAttribute("peliculas", modeloPelicula.getPeliculas());
        request.getRequestDispatcher("jsp/alquilerFormulario.jsp").forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedulaCliente");
        String tipoDeAlquiler = request.getParameter("tipoAlquiler");

        Ejemplar modeloEjemplar = new Ejemplar();
        List<Ejemplar> listaEjemplares = new ArrayList<>();

        String codigoBase = "codigoEjemplar";

        for (int i = 0; i < 20; i++) {
            String codigoEjemplar = request.getParameter(codigoBase + i);
            if (codigoEjemplar != null) {
                Ejemplar ejemplar = modeloEjemplar.getByCodigoEjemplar(codigoEjemplar);
                if (ejemplar != null) {
                    listaEjemplares.add(ejemplar);
                }
            } else {
                break;
            }
        }

        int numeroDias = Integer.parseInt(request.getParameter("numeroDias"));

        Cliente modeloCliente = new Cliente();

        Alquiler modeloAlquiler = new Alquiler();

        Cliente cliente = modeloCliente.getByCedula(cedula);
        List<Cliente> clientesConAlquileresPendientes = modeloAlquiler.getClientesByAlquileresNoDevueltos();
        boolean tieneAlquileresPendientes = clientesConAlquileresPendientes.contains(cliente);

        if (tieneAlquileresPendientes) {
            request.setAttribute("errorMensaje", "El cliente tiene alquileres pendientes. No puede realizar más alquileres.");
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
            return;
        }

        modeloAlquiler = new Alquiler();
        modeloAlquiler.getAlquileres();
        Alquiler alquiler = null;

        switch (tipoDeAlquiler) {
            case "normal":
                alquiler = modeloAlquiler.alquilar(numeroDias, listaEjemplares, cliente);
                break;
            case "fidelidad":
                alquiler = modeloAlquiler.alquilarFidelidad(numeroDias, listaEjemplares, cliente);
                break;
            default:
                break;
        }

        modeloAlquiler.create(alquiler);
        request.setAttribute("alquiler", alquiler);
        request.getRequestDispatcher("jsp/alquilerInfo.jsp").forward(request, response);
    }


    private void enlistarAlquileresNoDevueltos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Alquiler modeloAlquiler = new Alquiler();
        List<Alquiler> listaAlquileres = modeloAlquiler.getAlquileresNoDevueltos();

        request.setAttribute("listaAlquileres", listaAlquileres);
        request.getRequestDispatcher("jsp/ejemplaresAlquilados.jsp").forward(request, response);
    }

    private void mostrarError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
    }
}
