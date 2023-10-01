package main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.DTOs.PizzaDTO;
import main.model.Enfermeiro;
import main.model.EnfermeiroPlantao;
import main.model.Plantao;
import main.repositories.EnfermeiroRepository;
import main.service.EnfermeiroService;

@RestController
@RequestMapping("/enfermeiro/")
public class EnfermeiroController {
	@Autowired
	private EnfermeiroService service;
	@Autowired
	private EnfermeiroRepository repository;

	@GetMapping("listar")
	public List<?> listarEnfermeiros() {
		return service.findAllEnfermeiros();
	}
	
	@GetMapping("buscar/id/{id}")
	public Enfermeiro buscarEnfermeidoId(@PathVariable long id) {
		return service.getEnfermeiroId(id);
	}

	@GetMapping("buscar/{nome}")
	public List<Enfermeiro> buscarEnfermeidoNome(@PathVariable String nome) {
		String string = "";
		if(nome != null) {
			string = nome;
		}
		return service.getEnfermeiroNome(string);
	}

	@PostMapping("cadastrar")
	public ResponseEntity cadastrar(@RequestBody Enfermeiro enfermeiro) {
		return service.postEnfermeiro(enfermeiro);
	}

	@DeleteMapping("deletar/{id}")
	public List<?> deletar(@PathVariable long id) {
		repository.deleteById(id);
		return listarEnfermeiros();
	}

	@PutMapping("atualizar/{id}")
	public ResponseEntity atualizar(@RequestBody Enfermeiro enfermeiro, @PathVariable long id) {
		return service.atualizarEnfermeiro(id, enfermeiro);
	}

	@GetMapping("buscar/plantoes/{id}")
	public List<?> buscarPlantoes(@PathVariable long id) {
		return service.findPlantoesAgendados(id);
	}
	
	@PostMapping("adicionarPlantao/{idEnfermeiro}")
	public ResponseEntity adicionarPlantao(@RequestBody Plantao plantao, @PathVariable long idEnfermeiro) {
		return service.adicionarPlantao(plantao, idEnfermeiro);
	}

	@DeleteMapping("removerPlantao/{idPlantao}/{idEnfermeiro}")
	public void removerPlantao(@PathVariable long idPlantao, @PathVariable long idEnfermeiro) {
	 service.removerPlantao(idPlantao, idEnfermeiro);
	}
	//End-points para o dashBoard
	@GetMapping("/DashBoard/PizzaData")
	public PizzaDTO findPizzaData() {
		return service.buscarPizzaData();
	}
	
	
	
	
	
	
	
	
}
