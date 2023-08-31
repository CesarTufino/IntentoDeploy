package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.entidades.Usuario;

import java.io.IOException;

@WebServlet("/MenuController")
public class MenuController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MenuController() {
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
            case "inicioCajero":
                this.inicioCajero(request, response);
                break;
            case "inicioGerente":
                this.inicioGerente(request, response);
                break;
            case "alquilar":
                this.alquilar(request, response);
                break;
            case "devolver":
                this.devolver(request, response);
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

    private void inicioCajero(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/menuCajero.jsp").forward(request, response);
    }

    private void inicioGerente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/menuGerente.jsp").forward(request, response);
    }

    private void alquilar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("AlquilerController?ruta=inicio");
    }

    private void devolver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("DevolverController?ruta=inicio");
    }
    private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("ClienteController?ruta=inicio");
    }

}
