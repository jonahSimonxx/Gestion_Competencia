package model;

public class Competencia {

	private String nomCompetencia;
	private String idSede;
	private String estado;
	private String fechaIni;
	private String fechaFin;
	
	public Competencia(String nomCompetencia, String idSede, String estado, String fechaIni, String fechaFin) {
		this.nomCompetencia = nomCompetencia;
		this.idSede = idSede;
		this.estado = estado;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
	}

	public String getNomCompetencia() {
		return nomCompetencia;
	}

	public void setNomCompetencia(String nomCompetencia) {
		this.nomCompetencia = nomCompetencia;
	}

	public String getIdSede() {
		return idSede;
	}

	public void setIdSede(String idSede) {
		this.idSede = idSede;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
	
	
	
	
	
	

	
}
