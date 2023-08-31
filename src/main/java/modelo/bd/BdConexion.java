package modelo.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BdConexion {
    private static Connection cnn = null;

    private BdConexion() {
        String usuario = "Administrador";
        String password = "multipelis1234+";
        String url = "jdbc:sqlserver://multipelis-server.database.windows.net:1433;database=multipelisdb;user=Administrador@multipelis-server;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            cnn = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion con exito");
        } catch (SQLException e) {
            System.out.println("Error de conexión!!");
            e.printStackTrace();
        }
    }

    public static Connection getConexion() {
        if (cnn == null) {
            new BdConexion();
        }
        return cnn;
    }

    public static void cerrar(ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cerrar(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cerrar() {
        try {
            if (cnn != null) {
                cnn.close();
                cnn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
