package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.entidades.Usuario;

import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
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
            case "ingresar":
                this.ingresar(request, response);
                break;
            case "salir":
                this.salir(request, response);
                break;
            default:
                break;
        }
    }

    private void inicio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }

    private void ingresar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String contraseña = request.getParameter("contraseña");

        Usuario modeloUsuario = new Usuario();
        Usuario usuarioLogeado = modeloUsuario.autorizar(nombre,contraseña);

        if (usuarioLogeado != null) {
            // Crear la sesion
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogeado", usuarioLogeado);

            // Identificación del tipo de usuario
            if (usuarioLogeado.getEsGerente()){
                response.sendRedirect("MenuController?ruta=inicioGerente");
            } else{
                response.sendRedirect("MenuController?ruta=inicioCajero");
            }

        } else {
            String mensaje = "Ingresaste mal tu nombre de usuario o contraseña";
            request.setAttribute("errorMensaje", mensaje);
            request.getRequestDispatcher("jsp/error.jsp").forward(request, response);
        }
    }
    private void salir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }

}
