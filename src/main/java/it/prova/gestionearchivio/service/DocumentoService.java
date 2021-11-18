package it.prova.gestionearchivio.service;

import it.prova.gestionearchivio.model.Documento;

public interface DocumentoService {
	
	public Documento caricaSingoloElemento(Long id);
	
	public Documento caricaSingoloElementoEager(Long id);
	
	public void inserisciNuovo(Documento documentoInstance);

}
