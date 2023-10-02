package main.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.Enfermeiro;
import main.model.EnfermeiroPlantao;
import main.model.Plantao;

@Repository
public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Long> {
    public List<Enfermeiro> findByNomeContainingIgnoreCase(String nome);
    public Enfermeiro findByCoren(String coren);
    public long countByEnfermeiroTecnico(String nivel);
    public long count();
    @Query("SELECT e FROM Enfermeiro e WHERE e.coren = :coren AND e.idEnfermeiro <> :idEnfermeiro")
	public Enfermeiro findByCorenAndNotIdEnfermeiro(@Param("coren") String coren, @Param("idEnfermeiro") Long idEnfermeiro);
    
    @Query("SELECT r FROM Enfermeiro e JOIN e.relacionamentos r JOIN r.plantao p WHERE e.id = :idEnfermeiro AND p.dia >= :dia")
    public List<EnfermeiroPlantao> findPlantoesByDia(@Param("idEnfermeiro") long idEnfermeiro, @Param("dia") LocalDate dia);
}
