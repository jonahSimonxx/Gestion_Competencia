package model;

import java.sql.Date;

import utils.DateUtils;

public class Inscripcion {
	
	private String nomCompetencia;
	private String idAtleta;
	private Date fechaIni; 
    private Date fechaFin; 
	private String idEntrenador;
	
	
	public Inscripcion(String nomCompetencia, String idAtleta, String fechaIni, String fechaFin, String idEntrenador) {
		this.nomCompetencia = nomCompetencia;
		this.idAtleta = idAtleta;
		this.setFechaIni(fechaIni);
        this.setFechaFin(fechaFin);
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
	        return DateUtils.toDatabaseString(this.fechaIni);
	    }


	  public void setFechaIni(String fechaIni) {
	        java.util.Date utilDate = DateUtils.fromDatabaseString(fechaIni);
	        this.fechaIni = utilDate != null ? new Date(utilDate.getTime()) : null;
	    }


	  public String getFechaFin() {
	        return DateUtils.toDatabaseString(this.fechaFin);
	    }


	  public void setFechaFin(String fechaFin) {
	        java.util.Date utilDate = DateUtils.fromDatabaseString(fechaFin);
	        this.fechaFin = utilDate != null ? new Date(utilDate.getTime()) : null;
	    }
	
	public String getIdEntrenador() {
		return idEntrenador;
	}


	public void setIdEntrenador(String idEntrenador) {
		this.idEntrenador = idEntrenador;
	}

	
	

}
