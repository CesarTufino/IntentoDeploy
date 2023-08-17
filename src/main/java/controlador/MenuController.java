package controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String ruta = (request.getParameter("ruta") == null) ? "inicio" : request.getParameter("ruta");
        switch (ruta) {
            case "inicio":
                this.inicio(request, response);
                break;
            case "alquilar":
                this.alquilar(request, response);
                break;
            case "devolver":
                this.devolver(request, response);
                break;
            case "error":
                break;
            default:
                break;
        }
    }

    private void inicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/menu.jsp").forward(request, response);
    }

    private void alquilar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("AlquilerController?ruta=inicio");
    }

    private void devolver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("DevolverController?ruta=inicio");
    }

}
