package utils;

import model.Usuario;
import services.UsuarioServices;

public class Autenticador {
    private UsuarioServices usuarioServices;
    
    public Autenticador() {
        this.usuarioServices = new UsuarioServices();
    }
    
    public Usuario autenticar(String nombreUsuario, String contrasena) {
        return usuarioServices.autenticarUsuario(nombreUsuario, contrasena);
    }
    
    public boolean esAdmin(Usuario usuario) {
        return usuario != null && "admin".equalsIgnoreCase(usuario.getRol());
    }
}