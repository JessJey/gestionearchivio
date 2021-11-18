package it.prova.gestionearchivio.service;

import java.util.List;

import it.prova.gestionearchivio.dto.FascicoloDTO;
import it.prova.gestionearchivio.model.Fascicolo;

public interface FascicoloService {


	void inserisciNuovo(Fascicolo fascicoloIstance);

	public List<Fascicolo> listAllFascicoli();
	
	public List<Fascicolo> findByExample(FascicoloDTO example);
	
	public Fascicolo caricaSingoloFascicolo(Long id);
	
	public void aggiorna(Fascicolo fascicoloInstance);
}
