package it.prova.gestionearchivio.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionearchivio.model.Documento;

public interface DocumentoRepository extends CrudRepository<Documento, Long> {
	@Query("from Documento d join fetch d.fascicolo where d.id = ?1")
	Documento findSingleDocumentoEager(Long id);
}
