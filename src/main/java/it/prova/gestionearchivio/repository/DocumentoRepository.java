package it.prova.gestionearchivio.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionearchivio.model.Documento;

public interface DocumentoRepository extends CrudRepository<Documento, Long> {

}
