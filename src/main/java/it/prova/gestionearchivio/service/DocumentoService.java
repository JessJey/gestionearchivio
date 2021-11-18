package it.prova.gestionearchivio.service;

import java.util.List;

import it.prova.gestionearchivio.dto.DocumentoDTO;
import it.prova.gestionearchivio.model.Documento;

public interface DocumentoService {
	
	public void aggiorna(Documento documentoInstance);
	
	public List<Documento> listAllDocumenti();
	
	public Documento caricaSingoloElemento(Long id);
	
	public Documento caricaSingoloElementoEager(Long id);
	
	public void inserisciNuovo(Documento documentoInstance);
	
	public List<Documento> findByExample(DocumentoDTO example);

	
}
