package main.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class Plantao implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPlantao;
	private LocalDate dia;
	private LocalTime horario;
	@OneToMany(mappedBy = "plantao")
	@Cascade(CascadeType.ALL)
	@JsonBackReference
	private List<EnfermeiroPlantao> relacionamentos;
	
	Plantao() {
	};

	public long getIdPlantao() {
		return idPlantao;
	}

	public void setIdPlantao(long idPlantao) {
		this.idPlantao = idPlantao;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public List<EnfermeiroPlantao> getRelacionamentos() {
		return relacionamentos;
	}

	public void setRelacionamentos(List<EnfermeiroPlantao> relacionamentos) {
		this.relacionamentos = relacionamentos;
	}

}