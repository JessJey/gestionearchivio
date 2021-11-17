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

}
