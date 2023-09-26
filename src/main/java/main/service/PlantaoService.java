package main.service;

import java.io.Console;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import main.model.DataDTO;
import main.model.Enfermeiro;
import main.model.EnfermeiroPlantao;
import main.model.Plantao;
import main.repositories.EnfermeiroRepository;
import main.repositories.PlantaoRepository;
import main.repositories.RelacionamentoRepository;

@Service
public class PlantaoService {
	@Autowired
	private PlantaoRepository repository;
	@Autowired
	private EnfermeiroRepository enfermeiroRepository;
	@Autowired
	private RelacionamentoRepository relacionamentoRepository;

	public List<Plantao> findAllPlantao() {
		return repository.findAllOrderByDiaAndHorarioDesc();
	}

	public void postEnfermeiro(Plantao plantao) {
		repository.save(plantao);

	}
	
	public void deletePlantao(long id) {
		repository.deleteById(id);
	}

	public Plantao getPlantao(long id) {
		return repository.findById(id).get();
	}

	public void atualizarEnfermeiro(long idAtual, Plantao plantaoNovo) {
		Plantao plantaoAtual = repository.findById(idAtual).get();
		BeanUtils.copyProperties(plantaoNovo, plantaoAtual);
		plantaoAtual.setIdPlantao(idAtual);
		repository.save(plantaoAtual);
	}

	public List<?> getNaoEscalados(long id) {
		return repository.getEnfermeirosNaoRelacionadosAoPlantao(id);
	}

	public List<?> getEscalados(long idPlantao) {
		return repository.findById(idPlantao).get().getRelacionamentos();
	}

	public void adicionarRelacionamentoEnfermeiroPlantao(long idPlantao, long idEnfermeiro) {
		System.out.println("Enfermeiro adicionar");
		Plantao plantao = repository.findById(idPlantao).get();
		Enfermeiro enfermeiro = enfermeiroRepository.findById(idEnfermeiro).get();
		var relacionamento = new EnfermeiroPlantao(enfermeiro, plantao);
		relacionamentoRepository.save(relacionamento);
	}

	public void removerRelacionamentoEnfermeiroPlantao(long idPlantao, long idEnfermeiro) {
		var relacionamento = relacionamentoRepository.findEnfermeiroPlantaoByEnfermeiroPlantao(idEnfermeiro, idPlantao);
		relacionamentoRepository.delete(relacionamento);
	}
	public void atualizarPlantoes() {
		
	}
	//DashBoard
	public DataDTO buscarBarsData(LocalDate data, int id){
		long tecnicos = 0;
		long enfermeiros = 0;
		try {
			tecnicos = repository.countEnfermeirosByEnfermeiroTecnicoAndDia("Técnico", data);
			enfermeiros = repository.countEnfermeirosByEnfermeiroTecnicoAndDia("Enfermeiro", data);
		}catch (Exception e) {}
		return new DataDTO(id, data, enfermeiros, tecnicos, enfermeiros+tecnicos);
	}
	public List<DataDTO> buscarBarsDataFromSemana(LocalDate dia) {
	    List<DataDTO> lista = new ArrayList<>();
	    dia = dia.minusDays(3);
	    for (int i = 0; i < 7; i++) {
	        lista.add(buscarBarsData(dia, i));
	        dia = dia.plusDays(1);
	    }
	    return lista;
	}
	public List<DataDTO> buscarBarsDataFromMes(LocalDate dia){
		List<DataDTO> lista = new ArrayList<>();
	    dia = dia.withDayOfMonth(1);
	    for (int i = 0; i < 30; i++) {
	        lista.add(buscarBarsData(dia, i));
	        dia = dia.plusDays(1);
	    }
	    return lista;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
