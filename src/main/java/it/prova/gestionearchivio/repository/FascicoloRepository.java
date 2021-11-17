package it.prova.gestionearchivio.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionearchivio.model.Fascicolo;

public interface FascicoloRepository extends CrudRepository<Fascicolo, Long>, CustomFascicoloRepository{

}
