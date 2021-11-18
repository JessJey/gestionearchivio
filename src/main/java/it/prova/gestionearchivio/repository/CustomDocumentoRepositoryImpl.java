package it.prova.gestionearchivio.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionearchivio.dto.DocumentoDTO;
import it.prova.gestionearchivio.model.Documento;

public class CustomDocumentoRepositoryImpl implements CustomDocumentoRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Documento> findByExample(DocumentoDTO example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select d from Documento d join d.fascicolo f where d.id = d.id ");

		if (StringUtils.isNotBlank(example.getCodice())) {
			whereClauses.add(" d.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getCodice() + "%");
		}
		if (StringUtils.isNotBlank(example.getDescrizione())) {
			whereClauses.add(" d.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getDescrizione() + "%");
		}
		if (example.getDataCreazione() != null) {
			whereClauses.add("d.dataCreazione >= :dataCreazione ");
			paramaterMap.put("dataCreazione", example.getDataCreazione());
		}
		if (example.getDataUltimaModifica() != null) {
			whereClauses.add("d.dataUltimaModifica >= :dataUltimaModifica ");
			paramaterMap.put("dataUltimaModifica", example.getDataUltimaModifica());
		}
//		if (StringUtils.isNotBlank(example.getFascicoloProprietario().getCodice())) {
//			whereClauses.add(" f.codice =:codice ");
//			paramaterMap.put("codice", example.getFascicoloProprietario().getClass());
//		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Documento> typedQuery = entityManager.createQuery(queryBuilder.toString(), Documento.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
	
}