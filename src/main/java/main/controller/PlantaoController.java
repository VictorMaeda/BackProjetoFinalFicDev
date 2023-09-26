package main.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.model.DataDTO;
import main.model.Plantao;
import main.repositories.EnfermeiroRepository;
import main.repositories.PlantaoRepository;
import main.service.PlantaoService;

@RestController
@RequestMapping("/plantao/")
public class PlantaoController {
	@Autowired
	private PlantaoService service;
	@Autowired
	private PlantaoRepository repository;
	@Autowired
	private EnfermeiroRepository enfermeiroRepository;

//CRUD
	@GetMapping("listar")
	public List<?> listar() {
		return service.findAllPlantao();
	}

	@GetMapping("buscar/{id}")
	public Plantao buscarId(@PathVariable long id) {
		return service.getPlantao(id);
	}

//Consultar escalados de um plantão
	@GetMapping("buscar/escalados/{id}")
	public List<?> buscarEscalados(@PathVariable long id) {
		return service.getEscalados(id);
	}

//Consultar não escalados de um plantão
	@GetMapping("buscar/naoEscalados/{id}")
	public List<?> naoEscalados(@PathVariable long id) {
		return service.getNaoEscalados(id);
	}

//Remover escalado do plantao
	@DeleteMapping("removerEnfermeiro/{idPlantao}/{idEnfermeiro}")
	public List<?> removerEnfermeiro(@PathVariable long idPlantao, @PathVariable long idEnfermeiro) {
		try {
			service.removerRelacionamentoEnfermeiroPlantao(idPlantao, idEnfermeiro);
		}catch (Exception e) {
		}
		return buscarEscalados(idPlantao);
	}

//Adicionar escalado no plantao
	@PostMapping("adicionarEnfermeiro/{idPlantao}/{idEnfermeiro}")
	public List<?> adicionarEnfermeiro(@PathVariable long idPlantao, @PathVariable long idEnfermeiro) {
		service.adicionarRelacionamentoEnfermeiroPlantao(idPlantao, idEnfermeiro);
		return service.getNaoEscalados(idPlantao);
	}
	//DashBoard
	@GetMapping("DashBoard/BarsData/semana/{dia}")
	public List<DataDTO> buscarBarsDataSemana(@PathVariable LocalDate dia) {
		return service.buscarBarsDataFromSemana(dia);
	}
	@GetMapping("DashBoard/BarsData/mes/{dia}")
	public List<DataDTO> buscarBarsDataMes(@PathVariable LocalDate dia) {
		return service.buscarBarsDataFromMes(dia);
	}
	
	
	
	
	
	
	
	
	
	
	
}
