package it.prova.gestionearchivio.repository;

import java.util.List;

import it.prova.gestionearchivio.dto.FascicoloDTO;
import it.prova.gestionearchivio.model.Fascicolo;

public interface CustomFascicoloRepository {

	List<Fascicolo> findByExample(FascicoloDTO example);
}
