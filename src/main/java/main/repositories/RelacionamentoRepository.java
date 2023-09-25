package main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.Enfermeiro;
import main.model.EnfermeiroPlantao;
import main.model.Plantao;

@Repository
public interface RelacionamentoRepository extends JpaRepository<EnfermeiroPlantao, Long> {
	List<EnfermeiroPlantao> findByEnfermeiro(Enfermeiro enfermeiro);

	List<EnfermeiroPlantao> findByPlantao(Plantao plantao);

	@Query(value = "SELECT ep FROM EnfermeiroPlantao ep WHERE ep.enfermeiro.id = :enfermeiroId AND ep.plantao.id = :plantaoId")
	EnfermeiroPlantao findEnfermeiroPlantaoByEnfermeiroPlantao(@Param("enfermeiroId") Long enfermeiroId,
			@Param("plantaoId") Long plantaoId);
}
