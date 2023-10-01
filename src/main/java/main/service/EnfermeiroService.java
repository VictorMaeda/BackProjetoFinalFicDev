package main.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import main.DTOs.DataDTO;
import main.DTOs.PizzaDTO;
import main.model.Enfermeiro;
import main.model.EnfermeiroPlantao;
import main.model.Plantao;
import main.repositories.EnfermeiroRepository;
import main.repositories.PlantaoRepository;
import main.repositories.RelacionamentoRepository;

@Service
public class EnfermeiroService {
	@Autowired
	private EnfermeiroRepository repository;
	@Autowired
	private PlantaoRepository plantaoRepository;
	@Autowired
	private RelacionamentoRepository relacionamentoRepository;
	@Autowired
	private PlantaoService plantaoService;

//CRUD
	public List<Enfermeiro> findAllEnfermeiros() {
		return repository.findAll();
	}

	public ResponseEntity postEnfermeiro(Enfermeiro enfermeiro) {
		if (repository.findByCoren(enfermeiro.getCoren()) != null) {
			System.out.println("Já existe enfermeiro com esse coren");
			return ResponseEntity.badRequest().body("Já existe enfermeiro com esse coren");
		}
		repository.save(enfermeiro);
		return ResponseEntity.ok().build();
	}

	public void deleteEnfermeiro(long id) {
		repository.deleteById(id);
	}

	public Enfermeiro getEnfermeiroId(long id) {
		return repository.findById(id).get();
	}

	public ResponseEntity atualizarEnfermeiro(long idAtual, Enfermeiro enfermeiroNovo) {
		if (repository.findByCoren(enfermeiroNovo.getCoren()) != null) {
			if(repository.findByCoren(enfermeiroNovo.getCoren()).getIdEnfermeiro() != idAtual) {
				return ResponseEntity.badRequest().body("Já existe outro enfermeiro com esse coren");
			}
		}
		Enfermeiro enfermeiroAtual = repository.findById(idAtual).get();
		BeanUtils.copyProperties(enfermeiroNovo, enfermeiroAtual);
		enfermeiroAtual.setIdEnfermeiro(idAtual);
		repository.save(enfermeiroAtual);
		return ResponseEntity.ok().build();
	}

//Busca por nome
	public List<Enfermeiro> getEnfermeiroNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}

//Buscar plantoes de enfermeiro
	public List<?> findPlantoesAgendados(long id) {
		System.out.println(LocalDate.now());
		return repository.findPlantoesByDia(id, LocalDate.now());
	}
	public boolean validarNovoPlantao(Plantao plantao, Enfermeiro enfermeiro) {
		List<EnfermeiroPlantao> lista =  enfermeiro.getRelacionamentos();
		for (EnfermeiroPlantao enfermeiroPlantao : lista) {
			if(enfermeiroPlantao.getPlantao().getDia().toString().equals(plantao.getDia().toString())) {
				return false;
			}
		}
		return true;
	}
	public ResponseEntity adicionarPlantao(Plantao plantao, long idEnfermeiro) {
		System.out.println(idEnfermeiro);
		try {
			var enfermeiro = repository.findById(idEnfermeiro).get();
			var booleano = validarNovoPlantao(plantao, enfermeiro);
			if(booleano) {
				plantaoService.postEnfermeiro(plantao);
				plantao =  plantaoService.getPlantaoByDiaAndHorario(plantao);
				System.out.println("IdPlantao: " + plantao.getIdPlantao());
				plantaoService.adicionarRelacionamentoEnfermeiroPlantao(plantao.getIdPlantao(), idEnfermeiro);
				return ResponseEntity.ok().build();
			}else {
				return ResponseEntity.badRequest().body("Esse enfermeiro já tem um plantão agendado nesse dia");
			}
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("Ocorreu um erro");
		}
	}

	public void removerPlantao(long idPlantao, long idEnfermeiro) {
		var relacionamento = relacionamentoRepository.findEnfermeiroPlantaoByEnfermeiroPlantao(idEnfermeiro, idPlantao);
		relacionamentoRepository.delete(relacionamento);
	}
	
	public PizzaDTO buscarPizzaData() {
		var enfermeiros = repository.countByEnfermeiroTecnico("Enfermeiro");
		var tecnicos = repository.countByEnfermeiroTecnico("Técnico");
		return new PizzaDTO(enfermeiros, tecnicos);
	}
}
