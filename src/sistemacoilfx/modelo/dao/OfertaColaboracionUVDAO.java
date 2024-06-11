package sistemacoilfx.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import sistemacoilfx.modelo.ConexionBD;
import sistemacoilfx.modelo.pojo.OfertaColaboracionUV;
import sistemacoilfx.utilidades.Constantes;

public class OfertaColaboracionUVDAO {
    
    public static HashMap<String, Object> obtenerOfertasUVProfesorUV(Integer idProfesorUV){
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idOfertaColaboracionUV, nombreColaboracion, disciplina, objetivoCurso, perfilEstudiante, temaInteres, informacionAdicional, estado, o.idIdioma, i.nombre AS 'idioma', o.idAreaAcademica, a.nombre AS 'areaAcademica', o.idDependencia, d.nombre AS 'dependencia', o.idPeriodo, p.nombrePeriodo AS 'periodo', o.idProfesorUV FROM ofertacolaboracionuv o INNER JOIN idioma i ON o.idIdioma = i.idIdioma INNER JOIN areaacademica a ON o.idAreaAcademica = a.idAreaAcademica INNER JOIN dependencia d ON o.idDependencia = d.idDependencia INNER JOIN periodo p ON o.idPeriodo = p.idPeriodo INNER JOIN profesoruv pr ON o.idProfesorUV = pr.idProfesorUV WHERE o.idProfesorUV = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idProfesorUV);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<OfertaColaboracionUV> ofertasDeColaboracionUV = new ArrayList();
                while(resultado.next()){
                    OfertaColaboracionUV ofertaColaboracionUV = new OfertaColaboracionUV();
                    ofertaColaboracionUV.setIdOfertaColaboracionUV(resultado.getInt("idOfertaColaboracionUV"));
                    ofertaColaboracionUV.setNombre(resultado.getString("nombreColaboracion"));
                    ofertaColaboracionUV.setDisciplina(resultado.getString("disciplina"));
                    ofertaColaboracionUV.setObjetivoCurso(resultado.getString("objetivoCurso"));
                    ofertaColaboracionUV.setPerfilEstudiante(resultado.getString("perfilEstudiante"));
                    ofertaColaboracionUV.setTemaInteres(resultado.getString("temaInteres"));
                    ofertaColaboracionUV.setInformacionAdicional(resultado.getString("informacionAdicional"));
                    ofertaColaboracionUV.setEstado(resultado.getString("estado"));
                    ofertaColaboracionUV.setIdIdioma(resultado.getInt("idIdioma"));
                    ofertaColaboracionUV.setIdioma(resultado.getString("idioma"));
                    ofertaColaboracionUV.setIdAreaAcademica(resultado.getInt("idAreaAcademica"));
                    ofertaColaboracionUV.setAreaAcademica(resultado.getString("areaAcademica"));
                    ofertaColaboracionUV.setIdDependencia(resultado.getInt("idDependencia"));
                    ofertaColaboracionUV.setDependencia(resultado.getString("dependencia"));
                    ofertaColaboracionUV.setIdPeriodo(resultado.getInt("idPeriodo"));
                    ofertaColaboracionUV.setPeriodo(resultado.getString("periodo"));
                    ofertaColaboracionUV.setIdProfesorUV(resultado.getInt("idProfesorUV"));
                    ofertasDeColaboracionUV.add(ofertaColaboracionUV);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("ofertasUV", ofertasDeColaboracionUV);
                conexionBD.close();
            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }
    
    public static HashMap<String, Object> registrarOfertaColaboracionUV(OfertaColaboracionUV ofertaColaboracionUV){
        HashMap<String, Object> respuesta = new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            String sentencia = "INSERT INTO ofertacolaboracionuv (nombreColaboracion, disciplina, objetivoCurso, perfilEstudiante, temaInteres, informacionAdicional, idIdioma, idAreaAcademica, idDependencia, idProfesorUV, idPeriodo, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, ofertaColaboracionUV.getNombre());
                prepararSentencia.setString(2, ofertaColaboracionUV.getDisciplina());
                prepararSentencia.setString(3, ofertaColaboracionUV.getObjetivoCurso());
                prepararSentencia.setString(4, ofertaColaboracionUV.getPerfilEstudiante());
                prepararSentencia.setString(5, ofertaColaboracionUV.getTemaInteres());
                prepararSentencia.setString(6, ofertaColaboracionUV.getInformacionAdicional());
                prepararSentencia.setInt(7, ofertaColaboracionUV.getIdIdioma());
                prepararSentencia.setInt(8, ofertaColaboracionUV.getIdAreaAcademica());
                prepararSentencia.setInt(9, ofertaColaboracionUV.getIdDependencia());
                prepararSentencia.setInt(10, ofertaColaboracionUV.getIdProfesorUV());
                prepararSentencia.setInt(11, ofertaColaboracionUV.getIdPeriodo());
                prepararSentencia.setString(12, Constantes.ESTADO_EN_ESPERA);
                int filasAfectadas = prepararSentencia.executeUpdate();
                if(filasAfectadas > 0){
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, "Oferta de Colaboración UV registrada correctamente.");
                } else {
                    respuesta.put(Constantes.KEY_MENSAJE, "Lo sentimos, ha ocurrido un error al registrar la oferta de colaboración UV, favor de verificar la información.");
                }
                conexionBD.close();
            } catch(SQLException ex){
                ex.printStackTrace();
                respuesta.put(Constantes.KEY_MENSAJE, ex.getMessage());
            }
        } else {
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);            
        }
        return respuesta;
    }
}