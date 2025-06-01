package model;

import java.sql.Date;

public class Competencia {

	private String nomCompetencia;
	private String nomSede;
	private String estado;
	private Date fechaIni;
    private Date fechaFin;
	
	public Competencia(String nomCompetencia, String nomSede, String estado, String fechaIni, String fechaFin) {
		this.nomCompetencia = nomCompetencia;
		this.nomSede = nomSede;
		this.estado = estado;
		this.setFechaIni(fechaIni);
        this.setFechaFin(fechaFin);
	
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

	public Date getFechaIni() {
        return this.fechaIni;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni =  java.sql.Date.valueOf(fechaIni);

    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin =  java.sql.Date.valueOf(fechaFin);
    }
}
