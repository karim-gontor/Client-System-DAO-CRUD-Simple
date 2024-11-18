package zona_fit.conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        var baseDatos = "zona_fit_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        //Fake user
        var usuario = "NOMBRE DE USUARIO";
        //Fake password
        var password = "CONTRASEÑA";

        try {
            //Clase de conexión a mysql -> Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e){
            System.out.println("Error al conectar");
        }
        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();

        if (conexion != null){
            System.out.println("Conexión exitosa: " + conexion);
        } else {
            System.out.println("Error en la conexión: " + conexion);
        }
    }
}
