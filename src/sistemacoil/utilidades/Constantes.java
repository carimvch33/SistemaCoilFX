package sistemacoil.utilidades;

/**
 *
 * @author jafet
 */
public class Constantes {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String NOMBRE_BD = "coil_db";
    public static final String HOSTNAME = "localhost";
    public static final String PUERTO = "3306";
    public static final String USUARIO = "jaft";
    public static final String PASSWORD = "salamence";
    
    public static final int MAX_LONG_MATRICULA = 9;
    public static final int MAX_LONG_NOMBRES = 20;
    public static final int MAX_LONG_CORREO = 40;
    public static final int MAX_LONG_INSTITUCION = 40;
    public static final int MAX_LONG_TELEFONO = 10;
    public static final int MAX_LONG_NOMBRE_COLAB = 70;
    
    public static final String ESTADO_EN_ESPERA = "En Espera";
    public static final String ESTADO_ACEPTADA = "Aceptada";
    public static final String ESTADO_RECHAZADA = "Rechazada";
    
    public static final String ERROR_CONEXION = "Por el momento el servidor no se encuentra disponible, intentelo más tarde.";
    public static final String ERROR_CAMPOS_VACIOS = "Campo obligatorio.*";
    public static final String ERROR_CB_VACIO = "Selecciona una opción.*";
    public static final String ERROR_FORMATO_CORREO = "Dirección de correo inválida.*";
    public static final String ERROR_FORMATO_MATRICULA = "Matrícula inválida.*";
    
    public static final String KEY_MENSAJE = "mensaje";
    public static final String KEY_ERROR = "error";
}
