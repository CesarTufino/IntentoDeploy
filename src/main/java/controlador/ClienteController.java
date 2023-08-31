package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.entidades.*;

import java.io.IOException;

@WebServlet("/ClienteController")
public class ClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClienteController() {
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
                break;
            default:
                break;
        }
    }

    private void inicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente modeloCliente = new Cliente();
        request.setAttribute("clientes", modeloCliente.getClientes());
        request.getRequestDispatcher("jsp/registrarCliente.jsp").forward(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");

        Cliente modeloCliente = new Cliente();
        Cliente cliente = new Cliente(cedula,nombre,apellido);
        modeloCliente.create(cliente);

        response.sendRedirect("MenuController?ruta=inicioCajero");
    }

}
