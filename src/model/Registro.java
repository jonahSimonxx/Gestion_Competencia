package model;

public class Registro {
	
	private String idDisciplina;
	private String marcas;
	private String idAtleta;
	private String nomCompetencia;
	private int puesto;
	
	
	public Registro(String idDisciplina, String marcas, String idAtleta, String nomCompetencia, int puesto) {
		this.idDisciplina = idDisciplina;
		this.marcas = marcas;
		this.idAtleta = idAtleta;
		this.nomCompetencia = nomCompetencia;
		this.puesto = puesto;
	}


	public String getIdDisciplina() {
		return idDisciplina;
	}


	public void setIdDisciplina(String idDisciplina) {
		this.idDisciplina = idDisciplina;
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
