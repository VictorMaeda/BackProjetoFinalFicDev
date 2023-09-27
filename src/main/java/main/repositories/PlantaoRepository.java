package main.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.Enfermeiro;
import main.model.Plantao;

@Repository
public interface PlantaoRepository extends JpaRepository<Plantao, Long> {
	Plantao findByDia(Date dia);

	Plantao findByHorario(LocalTime horario);
	
	Plantao findByDiaAndHorario(LocalDate dia, LocalTime horario);
	@Query(value = "SELECT e FROM Enfermeiro e " + "WHERE e.id NOT IN "
			+ "(SELECT ep.enfermeiro.id FROM EnfermeiroPlantao ep WHERE ep.plantao.id = :plantaoId)")
	List<Enfermeiro> getEnfermeirosNaoRelacionadosAoPlantao(@Param("plantaoId") Long plantaoId);
	@Query("SELECT COUNT(ep) FROM EnfermeiroPlantao ep " +
		       "WHERE ep.enfermeiro.enfermeiroTecnico = :variavel1 " +
		       "AND ep.plantao.dia = :variavel2")
	
		long countEnfermeirosByEnfermeiroTecnicoAndDia(
		    @Param("variavel1") String variavel1,
		    @Param("variavel2") LocalDate variavel2
		);
	long countRelacionamentosByDia(LocalDate dia);
	@Query("SELECT p FROM Plantao p ORDER BY p.dia DESC, p.horario DESC")
	List<Plantao> findAllOrderByDiaAndHorarioDesc();

}