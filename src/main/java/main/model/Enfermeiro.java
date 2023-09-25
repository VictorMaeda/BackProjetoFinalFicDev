package main.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Enfermeiro implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEnfermeiro;
	private String nome;
	private String coren;
	private String enfermeiroTecnico;
	@OneToMany(mappedBy = "enfermeiro")
	@Cascade(CascadeType.ALL)
	@JsonBackReference
	private List<EnfermeiroPlantao> relacionamentos;
	
	Enfermeiro() {
	};

	public String getCoren() {
		return coren;
	}

	public void setCoren(String coren) {
		this.coren = coren;
	}

	public long getIdEnfermeiro() {
		return idEnfermeiro;
	}

	public void setIdEnfermeiro(long idEnfermeiro) {
		this.idEnfermeiro = idEnfermeiro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEnfermeiroTecnico() {
		return enfermeiroTecnico;
	}

	public void setEnfermeiroTecnico(String enfermeiroTecnico) {
		this.enfermeiroTecnico = enfermeiroTecnico;
	}

	public List<EnfermeiroPlantao> getRelacionamentos() {
		return relacionamentos;
	}

	public void setRelacionamentos(List<EnfermeiroPlantao> relacionamentos) {
		this.relacionamentos = relacionamentos;
	}
}
