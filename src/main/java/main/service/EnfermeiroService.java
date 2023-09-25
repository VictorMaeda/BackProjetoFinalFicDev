package main.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import main.model.DataDTO;
import main.model.Enfermeiro;
import main.model.PizzaDTO;
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
		if (repository.findByCorenAndNotIdEnfermeiro(enfermeiroNovo.getCoren(), idAtual) != null) {
			return ResponseEntity.badRequest().body("Já existe outro enfermeiro com esse coren");
		}
		Enfermeiro enfermeiroAtual = repository.findById(idAtual).get();
		BeanUtils.copyProperties(enfermeiroNovo, enfermeiroAtual);
		enfermeiroAtual.setIdEnfermeiro(idAtual);
		repository.save(enfermeiroAtual);
		return ResponseEntity.ok().build();
	}

//Busca por nome
	public List<Enfermeiro> getEnfermeiroNome(String nome) {
		return repository.findByNomeContaining(nome);
	}

//Buscar plantoes de enfermeiro
	public List<?> findPlantoes(long id) {
		return repository.findById(id).get().getRelacionamentos();
	}

	public void adicionarPlantao(long idPlantao, long idEnfermeiro) {
		plantaoService.adicionarRelacionamentoEnfermeiroPlantao(idPlantao, idEnfermeiro);
	}

	public void removerPlantao(long idPlantao, long idEnfermeiro) {
		plantaoService.removerRelacionamentoEnfermeiroPlantao(idPlantao, idEnfermeiro);
	}
	
	public PizzaDTO buscarPizzaData() {
		var enfermeiros = repository.countByEnfermeiroTecnico("Enfermeiro");
		var tecnicos = repository.countByEnfermeiroTecnico("Técnico");
		return new PizzaDTO(enfermeiros, tecnicos);
	}
}
