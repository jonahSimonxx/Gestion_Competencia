package model;

public class Sede {
	
	private String idSede;
	private String nomSede;
	private String idCiudad;
	
	
	public Sede(String idSede, String nomSede, String idCiudad) {
		this.idSede = idSede;
		this.nomSede = nomSede;
		this.idCiudad = idCiudad;
	}


	public String getIdSede() {
		return idSede;
	}


	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}


	public String getNomSede() {
		return nomSede;
	}


	public void setNomSede(String nomSede) {
		this.nomSede = nomSede;
	}


	public String getIdCiudad() {
		return idCiudad;
	}


	public void setId_ciudad(String idCiudad) {
		this.idCiudad = idCiudad;
	}
	
	

}
