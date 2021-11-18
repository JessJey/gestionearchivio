package it.prova.gestionearchivio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionearchivio.dto.DocumentoDTO;
import it.prova.gestionearchivio.model.Documento;
import it.prova.gestionearchivio.repository.DocumentoRepository;

@Service
public class DocumentoServiceImpl implements DocumentoService {
	
	@Autowired
	private DocumentoRepository repository;

	@Transactional(readOnly = true)
	public Documento caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Documento caricaSingoloElementoEager(Long id) {
		return repository.findSingleDocumentoEager(id);
	}
	
	@Transactional(readOnly = true)
	public List<Documento> findByExample(DocumentoDTO example) {
		return repository.findByExample(example);
	}

}
