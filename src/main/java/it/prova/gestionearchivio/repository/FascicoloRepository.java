package it.prova.gestionearchivio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionearchivio.model.Fascicolo;

public interface FascicoloRepository extends CrudRepository<Fascicolo, Long>, CustomFascicoloRepository{
	public List<Fascicolo> findByCodiceIgnoreCaseContainingOrDescrizioneIgnoreCaseContainingOrderByCodiceAsc(String codice, String descrizione);
}
