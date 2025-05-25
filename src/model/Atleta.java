package model;

public class Atleta {
     
	private String idAtleta;
	private String nomCompleto;
	private char sexo;
	private String catDep;
	private String idEntrenador;
	private int edad;
	private String nomPais;
	
	
	public Atleta(String idAtleta, String nomCompleto, char sexo, String catDep, String idEntrenador, int edad,String nomPais) {
		this.idAtleta = idAtleta;
		this.nomCompleto = nomCompleto;
		this.sexo = sexo;
		this.catDep = catDep;
		this.idEntrenador = idEntrenador;
		this.edad = edad;
		this.nomPais = nomPais;
		
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
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
