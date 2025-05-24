package model;

public class Inscripcion {
	
	private String nomCompetencia;
	private String idAtleta;
	private String fechaIni;
	private String fechaFin;
	private String idEntrenador;
	
	
	public Inscripcion(String nomCompetencia, String idAtleta, String fechaIni, String fechaFin, String idEntrenador) {
		this.nomCompetencia = nomCompetencia;
		this.idAtleta = idAtleta;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.idEntrenador = idEntrenador;
	}



	public String getNomCompetencia() {
		return nomCompetencia;
	}


	public void setNomCompetencia(String nomCompetencia) {
		this.nomCompetencia = nomCompetencia;
	}


	public String getIdAtleta() {
		return idAtleta;
	}


	public void setIdAtleta(String idAtleta) {
		this.idAtleta = idAtleta;
	}


	public String getFechaIni() {
		return fechaIni;
	}


	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}


	public String getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public String getIdEntrenador() {
		return idEntrenador;
	}


	public void setIdEntrenador(String idEntrenador) {
		this.idEntrenador = idEntrenador;
	}

	
	

}
