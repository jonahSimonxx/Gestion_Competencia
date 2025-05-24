package model;

public class Entrenador {
	
	private String idEntrenador;
	private String nombre;
	private String direccion;
	private String esp;
	
	
	public Entrenador(String idEntrenador, String nombre, String direccion, String esp) {
		this.idEntrenador = idEntrenador;
		this.nombre = nombre;
		this.direccion = direccion;
		this.esp = esp;
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
		return esp;
	}
	public void setEsp(String esp) {
		this.esp = esp;
	}
	
	

}
