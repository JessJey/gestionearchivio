package it.prova.gestionearchivio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionearchivio.model.Documento;
import it.prova.gestionearchivio.repository.DocumentoRepository;

@Service
public class DocumentoServiceImpl implements DocumentoService {
	
	@Autowired
	private DocumentoRepository repository;

	@Override
	public Documento caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Documento caricaSingoloElementoEager(Long id) {
		return repository.findSingleDocumentoEager(id);
	}

}
