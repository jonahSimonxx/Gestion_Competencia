package model;

import java.sql.Date;

import utils.DateUtils;

public class Competencia {

	private String nomCompetencia;
	private String nomSede;
	private String estado;
	private Date fechaIni;
    private Date fechaFin;
	private String nomDisciplina;
	
	public Competencia(String nomCompetencia, String nomSede, String estado, String fechaIni, String fechaFin, String nomDisciplina) {
		this.nomCompetencia = nomCompetencia;
		this.nomSede = nomSede;
		this.estado = estado;
		this.setFechaIni(fechaIni);
        this.setFechaFin(fechaFin);
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
        return DateUtils.toDatabaseString(this.fechaIni);
    }

    public String getFechaFin() {
        return DateUtils.toDatabaseString(this.fechaFin);
    }

    public void setFechaIni(String fechaIni) {
        java.util.Date utilDate = DateUtils.fromDatabaseString(fechaIni);
        this.fechaIni = utilDate != null ? new Date(utilDate.getTime()) : null;
    }

    public void setFechaFin(String fechaFin) {
        java.util.Date utilDate = DateUtils.fromDatabaseString(fechaFin);
        this.fechaFin = utilDate != null ? new Date(utilDate.getTime()) : null;
    }

	
	
	
	
	
	

	
}
