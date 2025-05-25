package model;

public class Entrenador {
	
	private String idEntrenador;
	private String nombre;
	private String direccion;
	private String espec;
	
	
	public Entrenador(String idEntrenador, String nombre, String direccion, String espec) {
		this.idEntrenador = idEntrenador;
		this.nombre = nombre;
		this.direccion = direccion;
		this.espec = espec;
	}
	public String getIdEntrenador() {
		return idEntrenador;
	}
	public void setIdEntrenador(String idEntrenador) {
		this.idEntrenador = idEntrenador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEsp() {
		return espec;
	}
	public void setEsp(String espec) {
		this.espec = espec;
	}
	
	

}
