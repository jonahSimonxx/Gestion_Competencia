package model;

public class Sede {
	
	private String nomSede;
	private String nomCiudad;
	
	
	public Sede(String nomSede, String nomCiudad) {
		this.nomSede = nomSede;
		this.nomCiudad = nomCiudad;
	}


	public String getNomSede() {
		return nomSede;
	}


	public void setNomSede(String nomSede) {
		this.nomSede = nomSede;
	}


	public String getNomCiudad() {
		return nomCiudad;
	}


	public void setNomCiudad(String nomCiudad) {
		this.nomCiudad = nomCiudad;
	}
	
	

}
