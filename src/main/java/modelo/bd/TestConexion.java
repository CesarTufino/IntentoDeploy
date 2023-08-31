package modelo.bd;

import java.sql.Connection;

public abstract class TestConexion {

    public static void main(String[] args) {

        Connection cnn = BdConexion.getConexion();
        if (cnn != null) {
            System.out.println("Tenemos conexion");
        } else {
            System.out.println("Error");
        }

    }

}
