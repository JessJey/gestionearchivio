package it.prova.gestionearchivio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionearchivio.model.Fascicolo;
import it.prova.gestionearchivio.repository.FascicoloRepository;

@Service
public class FascicoloServiceImpl implements FascicoloService {

	@Autowired
	private FascicoloRepository repository;

	
	@Transactional
	public void inserisciNuovo(Fascicolo fascicoloIstance) {
		repository.save(fascicoloIstance);
	}
}
