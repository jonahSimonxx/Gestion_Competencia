package model;

public class Registro {
	
	private String nomDisciplina;
	private String marcas;
	private String idAtleta;
	private String nomCompetencia;
	private int puesto;
	
	
	public Registro(String nomDisciplina, String marcas, String idAtleta, String nomCompetencia, int puesto) {
		this.nomDisciplina = nomDisciplina;
		this.marcas = marcas;
		this.idAtleta = idAtleta;
		this.nomCompetencia = nomCompetencia;
		this.puesto = puesto;
	}


	public String getNomDisciplina() {
		return nomDisciplina;
	}


	public void setNomDisciplina(String nomDisciplina) {
		this.nomDisciplina = nomDisciplina;
	}


	public String getMarcas() {
		return marcas;
	}


	public void setMarcas(String marcas) {
		this.marcas = marcas;
	}


	public String getIdAtleta() {
		return idAtleta;
	}


	public void setIdAtleta(String idAtleta) {
		this.idAtleta = idAtleta;
	}


	public String getNomCompetencia() {
		return nomCompetencia;
	}


	public void setNomCompetencia(String nomCompetencia) {
		this.nomCompetencia = nomCompetencia;
	}


	public int getPuesto() {
		return puesto;
	}


	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}
	
	
	

}
