package model;

public class Disciplina {
	
	private String idDisciplina;
	private String nomDisciplina;
	
	public Disciplina(String idDisciplina, String nomDisciplina) {
		this.idDisciplina = idDisciplina;
		this.nomDisciplina = nomDisciplina;
	}

	public String getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(String idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNomDisciplina() {
		return nomDisciplina;
	}

	public void setNomDisciplina(String nomDisciplina) {
		this.nomDisciplina = nomDisciplina;
	}
	
	

}
