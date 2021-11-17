package it.prova.gestionearchivio.repository;

import java.util.List;

import it.prova.gestionearchivio.dto.DocumentoDTO;
import it.prova.gestionearchivio.model.Documento;

public interface CustomDocumentoRepository {
	
	List<Documento> findByExample(DocumentoDTO example);

}
