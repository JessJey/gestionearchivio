package it.prova.gestionearchivio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionearchivio.dto.FascicoloDTO;
import it.prova.gestionearchivio.model.Fascicolo;
import it.prova.gestionearchivio.repository.FascicoloRepository;

@Service
public class FascicoloServiceImpl implements FascicoloService {

	@Autowired
	private FascicoloRepository fascicoloRepository;

	@Transactional
	public void inserisciNuovo(Fascicolo fascicoloIstance) {
		fascicoloRepository.save(fascicoloIstance);
	}

	@Override
	@Transactional
	public List<Fascicolo> findByExample(FascicoloDTO example) {
		return fascicoloRepository.findByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Fascicolo> listAllFascicoli() {
		return (List<Fascicolo>) fascicoloRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Fascicolo caricaSingoloFascicolo(Long id) {
		return fascicoloRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Fascicolo fascicoloInstance) {
		fascicoloRepository.save(fascicoloInstance);
	}

	public Fascicolo caricaSingoloElemento(Long idFascicolo) {
		return fascicoloRepository.findById(idFascicolo).orElse(null);
	}

	@Override
	@Transactional
	public void rimuoviId(Long idFascicolo) {
		fascicoloRepository.deleteById(idFascicolo);
	}

}
