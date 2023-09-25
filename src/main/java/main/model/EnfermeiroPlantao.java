package main.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EnfermeiroPlantao implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEnfermeiroPlantao;
	@ManyToOne
	private Enfermeiro enfermeiro;
	@ManyToOne
	private Plantao plantao;
	
	public EnfermeiroPlantao() {
	};

	public EnfermeiroPlantao(Enfermeiro enfermeiro, Plantao plantao) {
		super();
		this.enfermeiro = enfermeiro;
		this.plantao = plantao;
	}

	public long getIdEnfermeiroPlantao() {
		return idEnfermeiroPlantao;
	}

	public void setIdEnfermeiroPlantao(long idEnfermeiroPlantao) {
		this.idEnfermeiroPlantao = idEnfermeiroPlantao;
	}

	public Enfermeiro getEnfermeiro() {
		return enfermeiro;
	}

	public void setEnfermeiro(Enfermeiro enfermeiro) {
		this.enfermeiro = enfermeiro;
	}

	public Plantao getPlantao() {
		return plantao;
	}

	public void setPlantao(Plantao plantao) {
		this.plantao = plantao;
	}

}
