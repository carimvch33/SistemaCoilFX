package sistemacoilfx.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemacoilfx.modelo.ConexionBD;
import sistemacoilfx.modelo.pojo.AreaAcademica;
import sistemacoilfx.modelo.pojo.Dependencia;
import sistemacoilfx.modelo.pojo.Idioma;
import sistemacoilfx.modelo.pojo.Periodo;

public class CatalogoDAO {
    
    public static List<Periodo> obtenerPeriodos(){
        List<Periodo> periodos = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idPeriodo, nombrePeriodo, anioPeriodo, inicioPeriodo, finPeriodo FROM periodo";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while(resultado.next()){
                    Periodo periodo = new Periodo();
                    periodo.setIdPeriodo(resultado.getInt("idPeriodo"));
                    periodo.setNombrePeriodo(resultado.getString("nombrePeriodo"));
                    periodo.setAnioPeriodo(resultado.getString("anioPeriodo"));
                    periodo.setInicioPeriodo(resultado.getString("inicioPeriodo"));
                    periodo.setFinPeriodo(resultado.getString("finPeriodo"));
                    periodos.add(periodo);
                }
                conexionBD.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return periodos;
    }
    
    public static List<Idioma> obtenerIdiomas(){
        List<Idioma> idiomas = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idIdioma, nombre FROM idioma";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while(resultado.next()){
                    Idioma idioma = new Idioma();
                    idioma.setIdIdioma(resultado.getInt("idIdioma"));
                    idioma.setNombre(resultado.getString("nombre"));
                    idiomas.add(idioma);
                }
                conexionBD.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return idiomas;
    }
    
    public static List<AreaAcademica> obtenerAreasAcademicas(){
        List<AreaAcademica> areasAcademicas = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idAreaAcademica, nombre, clave FROM areaacademica";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while(resultado.next()){
                    AreaAcademica areaAcademica = new AreaAcademica();
                    areaAcademica.setIdAreaAcademica(resultado.getInt("idAreaAcademica"));
                    areaAcademica.setNombre(resultado.getString("nombre"));
                    areaAcademica.setClave(resultado.getString("clave"));
                    areasAcademicas.add(areaAcademica);
                }
                conexionBD.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return areasAcademicas;
    }
    
    public static List<Dependencia> obtenerDependencias(){
        List<Dependencia> dependencias = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConexion();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idDependencia, nombre, campus, municipio, clave, idAreaAcademica FROM dependencia";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while(resultado.next()){
                    Dependencia dependencia = new Dependencia();
                    dependencia.setIdDependencia(resultado.getInt("idDependencia"));
                    dependencia.setNombre(resultado.getString("nombre"));
                    dependencia.setCampus(resultado.getString("campus"));
                    dependencia.setMunicipio(resultado.getString("municipio"));
                    dependencia.setClave(resultado.getString("clave"));
                    dependencia.setIdAreaAcademica(resultado.getInt("idAreaAcademica"));
                    dependencias.add(dependencia);
                }
                conexionBD.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return dependencias;
    }
}