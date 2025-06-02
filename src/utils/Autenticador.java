package utils;

public class Autenticador {
    public static boolean validarCredenciales(String usuario, String contrasena, boolean esAdmin) {
        boolean credencialesValidas = false;
        
        if(esAdmin) {
            credencialesValidas = "admin".equals(usuario) && "admin".equals(contrasena);
        } else {
            credencialesValidas = "usuario".equals(usuario) && "usuario".equals(contrasena);
        }
        
        return credencialesValidas;
    }
}