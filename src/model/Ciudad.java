package model;

public class Ciudad {

	private String idCiudad;
	private String nomCiudad;
	private String nomPais;
	
	
	public Ciudad(String idCiudad, String nomCiudad, String nomPais) {
		this.idCiudad = idCiudad;
		this.nomCiudad = nomCiudad;
		this.nomPais = nomPais;
	}


	public String getIdCiudad() {
		return idCiudad;
	}


	public void setIdCiudad(String idCiudad) {
		this.idCiudad = idCiudad;
	}


	public String getNomCiudad() {
		return nomCiudad;
	}


	public void setNomCiudad(String nomCiudad) {
		this.nomCiudad = nomCiudad;
	}


	public String getIdPais() {
		return nomPais;
	}


	public void setIdPais(String nomPais) {
		this.nomPais = nomPais;
	}
	
	
	

}
