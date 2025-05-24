package model;

public class Atleta {
     
	private String idAtleta;
	private String nomCompleto;
	private String sexo;
	private String catDep;
	private String idEntrenador;
	private String nomPais;
	private int edad;
	
	public Atleta(String idAtleta, String nomCompleto, String sexo, String catDep, String idEntrenador, String nomPais,
			int edad) {
		this.idAtleta = idAtleta;
		this.nomCompleto = nomCompleto;
		this.sexo = sexo;
		this.catDep = catDep;
		this.idEntrenador = idEntrenador;
		this.nomPais = nomPais;
		this.edad = edad;
	}

	public String getIdAtleta() {
		return idAtleta;
	}

	public void setIdAtleta(String idAtleta) {
		this.idAtleta = idAtleta;
	}

	public String getNomCompleto() {
		return nomCompleto;
	}

	public void setNomCompleto(String nomCompleto) {
		this.nomCompleto = nomCompleto;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCatDep() {
		return catDep;
	}

	public void setCatDep(String catDep) {
		this.catDep = catDep;
	}

	public String getIdEntrenador() {
		return idEntrenador;
	}

	public void setIdEntrenador(String idEntrenador) {
		this.idEntrenador = idEntrenador;
	}

	public String getNomPais() {
		return nomPais;
	}

	public void setNomPais(String nomPais) {
		this.nomPais = nomPais;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
	
	
	
	
	
	
	
	
	
}
