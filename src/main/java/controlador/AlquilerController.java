package controlador;

import modelo.entidades.Alquiler;
import modelo.entidades.Cliente;
import modelo.entidades.Ejemplar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.entidades.Pelicula;

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

        for (int i = 0; i<20 ; i++){
            String codigoEjemplar = request.getParameter(codigoBase+i);
            if (codigoEjemplar!=null){
                Ejemplar ejemplar = modeloEjemplar.getByCodigoEjemplar(codigoEjemplar);
                if (ejemplar!=null) {
                    listaEjemplares.add(ejemplar);
                }
            } else{
                break;
            }
        }


        int numeroDias = Integer.parseInt(request.getParameter("numeroDias"));

        Cliente modeloCliente = new Cliente();
        Cliente cliente = modeloCliente.getByCedula(cedula);



        Alquiler modeloAlquiler = new Alquiler();
        modeloAlquiler.getAlquileres();
        Alquiler alquiler = null;

        switch (tipoDeAlquiler){
            case "normal":
                alquiler = modeloAlquiler.alquilar(numeroDias,listaEjemplares,cliente);
                break;
            case "fidelidad":
                alquiler = modeloAlquiler.alquilarFidelidad(numeroDias,listaEjemplares,cliente);
                break;
            default:
                break;
        }

        modeloAlquiler.create(alquiler);

        request.setAttribute("alquiler", alquiler);
        request.getRequestDispatcher("jsp/alquilerInfo.jsp").forward(request, response);
    }

}
