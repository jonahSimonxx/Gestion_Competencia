package model;

import java.sql.Date;

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


	public Date getFechaIni() {
        return this.fechaIni;
    }


  public void setFechaIni(String fechaIni) {
	  this.fechaIni= java.sql.Date.valueOf(fechaIni);

    }


  public Date getFechaFin() {
        return this.fechaFin;
    }


  public void setFechaFin(String fechaFin) {
	  this.fechaFin = java.sql.Date.valueOf(fechaFin);
    }

public String getIdEntrenador() {
	return idEntrenador;
}


public void setIdEntrenador(String idEntrenador) {
	this.idEntrenador = idEntrenador;
}

	
	

}
