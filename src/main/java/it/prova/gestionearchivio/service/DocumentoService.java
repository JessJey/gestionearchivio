package it.prova.gestionearchivio.service;

import java.util.List;

import it.prova.gestionearchivio.dto.DocumentoDTO;
import it.prova.gestionearchivio.model.Documento;

public interface DocumentoService {
	
	public Documento caricaSingoloElemento(Long id);
	
	public Documento caricaSingoloElementoEager(Long id);
	
	public List<Documento> findByExample(DocumentoDTO example);

}
