package model;

public class Competencia {

	private String nomCompetencia;
	private String nomSede;
	private String estado;
	private String fechaIni;
	private String fechaFin;
	private String nomDisciplina;
	
	public Competencia(String nomCompetencia, String nomSede, String estado, String fechaIni, String fechaFin, String nomDisciplina) {
		this.nomCompetencia = nomCompetencia;
		this.nomSede = nomSede;
		this.estado = estado;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.nomDisciplina = nomDisciplina;
	}

	public String getNomDisciplina() {
		return nomDisciplina;
	}

	public void setNomDisciplina(String nomDisciplina) {
		this.nomDisciplina = nomDisciplina;
	}

	public String getNomCompetencia() {
		return nomCompetencia;
	}

	public void setNomCompetencia(String nomCompetencia) {
		this.nomCompetencia = nomCompetencia;
	}

	public String getNomSede() {
		return nomSede;
	}

	public void setNomSede(String nomSede) {
		this.nomSede = nomSede;
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
