package sistemacoil.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemacoil.modelo.ConexionBD;
import sistemacoil.modelo.pojo.ProfesorExterno;
import sistemacoil.utilidades.Constantes;

/**
 *
 * @author jafet
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemacoil.modelo.ConexionBD;
import sistemacoil.modelo.pojo.ProfesorExterno;
import sistemacoil.modelo.pojo.Universidad;
import sistemacoil.utilidades.Constantes;

public class ProfesorExternoDAO {

    public static List<ProfesorExterno> obtenerProfesoresExternosPorIdioma(int idIdioma) {
        List<ProfesorExterno> profesoresExternos = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT pe.idProfesorExterno, pe.nombre, pe.apellidoPaterno, pe.apellidoMaterno, pe.departamento, "
                        + "pe.carrera, pe.correo, pe.telefono, pe.idUniversidad, pe.idIdioma, u.abreviatura "
                        + "FROM profesorExterno pe "
                        + "JOIN universidad u ON pe.idUniversidad = u.idUniversidad "
                        + "WHERE pe.idIdioma = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idIdioma);
                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {
                    ProfesorExterno profesorExterno = new ProfesorExterno();
                    profesorExterno.setIdProfesorExterno(resultado.getInt("idProfesorExterno"));
                    profesorExterno.setNombre(resultado.getString("nombre"));
                    profesorExterno.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    profesorExterno.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    profesorExterno.setDepartamento(resultado.getString("departamento"));
                    profesorExterno.setCarrera(resultado.getString("carrera"));
                    profesorExterno.setCorreo(resultado.getString("correo"));
                    profesorExterno.setTelefono(resultado.getString("telefono"));
                    profesorExterno.setIdUniversidad(resultado.getInt("idUniversidad"));
                    profesorExterno.setIdIdioma(resultado.getInt("idIdioma"));
                    String abreviaturaUniversidad = resultado.getString("abreviatura");
                    profesorExterno.setAbreviaturaUniversidad(abreviaturaUniversidad);
                    profesoresExternos.add(profesorExterno);
                }
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Constantes.ERROR_CONEXION);
        }
        return profesoresExternos;
    }
    
    public static boolean consultarExistenciaProfesorExterno(String nombre, String apellidoPaterno, String apellidoMaterno) {
        boolean esDuplicado = false;
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT COUNT(*) "
                        + "FROM profesorExterno "
                        + "WHERE nombre = ? AND apellidoPaterno = ? AND apellidoMaterno = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, nombre);
                prepararSentencia.setString(2, apellidoPaterno);
                prepararSentencia.setString(3, apellidoMaterno);
                ResultSet resultado = prepararSentencia.executeQuery();
                if (resultado.next() && resultado.getInt(1) > 0)
                    esDuplicado = true;
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Constantes.ERROR_CONEXION);
        }
        return esDuplicado;
    }

    public static String obtenerAbreviaturaUniversidad(int idUniversidad) {
        String abreviatura = null;
        Connection conexionBD = ConexionBD.obtenerConexion();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT abreviatura FROM universidad WHERE idUniversidad = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idUniversidad);
                ResultSet resultado = prepararSentencia.executeQuery();
                if (resultado.next()) {
                    abreviatura = resultado.getString("abreviatura");
                }
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Constantes.ERROR_CONEXION);
        }
        return abreviatura;
    }
    
    
}

