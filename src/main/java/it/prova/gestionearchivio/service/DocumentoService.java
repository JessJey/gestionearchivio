package it.prova.gestionearchivio.service;

import java.util.List;

import it.prova.gestionearchivio.dto.DocumentoDTO;
import it.prova.gestionearchivio.model.Documento;
import it.prova.gestionearchivio.model.Fascicolo;

public interface DocumentoService {
	
	public List<Documento> listAllDocumenti();
	
	public Documento caricaSingoloElemento(Long id);
	
	public Documento caricaSingoloElementoEager(Long id);
	
	public void inserisciNuovo(Documento documentoInstance);
	
	public List<Documento> findByExample(DocumentoDTO example);

	
}
